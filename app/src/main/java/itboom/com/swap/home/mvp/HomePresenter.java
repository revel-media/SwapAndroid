package itboom.com.swap.home.mvp;

import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itboom.com.swap.pojo.home.HomeResponse;
import itboom.com.swap.support.NetworkResult;

public class HomePresenter implements HomeMVP.Presenter {

    HomeMVP.View view;
    HomeMVP.Model model;
    Disposable disposable;

    public HomePresenter(HomeMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(HomeMVP.View view) {
        this.view = view;
    }

    @Override
    public void onLoad(String userId) {
        if (view != null){
            model.getHomeData(userId, null)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<HomeResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(HomeResponse homeResponse) {
                            if (homeResponse.isError()){
                                if (!homeResponse.getMessage().isEmpty()){
                                    view.onLoadFailure(NetworkResult.UNEXPECTED_ERROR, homeResponse.getMessage());
                                }
                            } else {
                                view.onLoadSuccess(new ArrayList<>(Arrays.asList(homeResponse.getItems())));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("RESULT_ERROR", "onError: " + e.getMessage());
                            view.onLoadFailure(NetworkResult.UNEXPECTED_ERROR, null);
                        }
                    });
        }
    }

    @Override
    public void onAskForSwap(JsonObject object) {

    }

    @Override
    public void onDetails(String id) {

    }

    @Override
    public void rxUnsubscribe() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
