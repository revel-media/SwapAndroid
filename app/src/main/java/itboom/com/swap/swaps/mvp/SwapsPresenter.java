package itboom.com.swap.swaps.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itboom.com.swap.pojo.home.Item;
import itboom.com.swap.pojo.my_items.MyItemsResponse;
import itboom.com.swap.pojo.swaps.SwapsResponse;
import itboom.com.swap.support.NetworkResult;

public class SwapsPresenter implements SwapsMVP.Presenter {

    SwapsMVP.View view;
    SwapsMVP.Model model;
    Disposable disposable;

    public SwapsPresenter(SwapsMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(SwapsMVP.View view) {
        this.view = view;
    }

    @Override
    public void getItems(String userId) {
        if (view != null){
            model.getItems(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<MyItemsResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(MyItemsResponse myItemsResponse) {
                            if (myItemsResponse != null){
                                if (myItemsResponse.isError()){
                                    /*todo no result found -no items available-*/
                                    if (myItemsResponse.getMessage().contentEquals("no items found")){
                                        view.onItemsFailure(NetworkResult.EMPTY_RESULT);
                                    } else {
                                        view.onItemsFailure(NetworkResult.INVALID_CREDENTIALS);
                                    }
                                } else {
                                    view.onItemsSuccess(new ArrayList<Item>(Arrays.asList(myItemsResponse.getItems())));
                                }
                            } else {
                                view.onItemsFailure(NetworkResult.UNEXPECTED_ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("RESULT_ERROR", "onError: " + e.getMessage());
                            view.onItemsFailure(NetworkResult.UNEXPECTED_ERROR);
                        }
                    });
        }
    }

    @Override
    public void getSwaps(String userId) {
        if (view != null){
            model.getOngoingRequests(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SwapsResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(SwapsResponse swapsResponse) {
                            if (swapsResponse != null){
                                if (swapsResponse.isError()){
                                    if (swapsResponse.getMessage().contentEquals("no requests have been found")){
                                        view.onSwapsFailure(NetworkResult.EMPTY_RESULT);
                                    } else {
                                        view.onSwapsFailure(NetworkResult.UNEXPECTED_ERROR);
                                    }
                                } else {
                                    view.onSwapsSuccess(new ArrayList<>(Arrays.asList(swapsResponse.getSwaps())));
                                }
                            } else {
                                view.onSwapsFailure(NetworkResult.UNEXPECTED_ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("RESULT_ERROR", "onError: " + e.getMessage());
                            view.onSwapsFailure(NetworkResult.UNEXPECTED_ERROR);
                        }
                    });
        }
    }

    @Override
    public void rxUnsubscribe() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
