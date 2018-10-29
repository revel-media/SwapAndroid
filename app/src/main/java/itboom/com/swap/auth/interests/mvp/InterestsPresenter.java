package itboom.com.swap.auth.interests.mvp;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itboom.com.swap.pojo.interests.Interest;
import itboom.com.swap.pojo.interests.InterestsResponse;
import itboom.com.swap.support.NetworkResult;

public class InterestsPresenter implements SelectInterestsMVP.Presenter {

    SelectInterestsMVP.View view;
    SelectInterestsMVP.Model model;
    Disposable disposable;

    public InterestsPresenter(SelectInterestsMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(SelectInterestsMVP.View view) {
        this.view = view;
    }

    @Override
    public void onViewLoad() {
        if (view != null) {
            view.showProgress();
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
                            view.hideProgress();
                            if (interestsResponse != null){
                                if (interestsResponse.isError()){
                                    view.onFailure(NetworkResult.UNEXPECTED_ERROR);
                                } else {
                                    view.onSuccess(new ArrayList<>(Arrays.asList(interestsResponse.getInterests())));
                                }
                            } else {
                                view.onFailure(NetworkResult.NETWORK_ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideProgress();
                            Log.i("RESULT_ERROR", "onError: " + e.getMessage());
                            view.onFailure(NetworkResult.UNEXPECTED_ERROR);
                        }
                    });
        }
    }

    @Override
    public void submitInterests(ArrayList<String> interests, String userId) {
        if (view != null){
            view.showProgress();
            JsonObject object = new JsonObject();
            object.addProperty("userId", userId);
            JsonArray array = new JsonArray();
            for (String i:interests) {
                array.add(i);
            }
            object.add("interests", array);

            model.submitInterests(object)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onComplete() {
                            view.hideProgress();
                            view.navigateToApp();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideProgress();
                            Log.i("RESULT_ERROR", "onError: " + e);
                            view.onFailure(NetworkResult.UNEXPECTED_ERROR);
                        }
                    });
        }
    }

    @Override
    public void rxUnsubscribe() {
        if (view != null){
            if (disposable != null && !disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
