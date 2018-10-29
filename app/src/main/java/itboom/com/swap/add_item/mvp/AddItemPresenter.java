package itboom.com.swap.add_item.mvp;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itboom.com.swap.pojo.interests.InterestsResponse;
import itboom.com.swap.support.NetworkResult;

public class AddItemPresenter implements AddItemMVP.Presenter {

    AddItemMVP.View view;
    AddItemMVP.Model model;
    private Disposable disposable;

    public AddItemPresenter(AddItemMVP.Model model) {
        this.model = model;
    }


    @Override
    public void setView(AddItemMVP.View view) {
        this.view = view;
    }

    @Override
    public void getInterests() {
        if (view != null){
            model.getAllInterests()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<InterestsResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(InterestsResponse interestsResponse) {
                            if (interestsResponse != null){
                                if (interestsResponse.isError()){
                                    view.onInterestsError(NetworkResult.EMPTY_RESULT);
                                } else {
                                    view.onInterestsSuccess(new ArrayList<>(Arrays.asList(interestsResponse.getInterests())));
                                }
                            } else {
                                view.onInterestsError(NetworkResult.NETWORK_ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.onInterestsError(NetworkResult.NETWORK_ERROR);
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
