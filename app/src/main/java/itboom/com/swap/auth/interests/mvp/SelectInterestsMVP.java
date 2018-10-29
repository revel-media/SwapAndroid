package itboom.com.swap.auth.interests.mvp;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Single;
import itboom.com.swap.pojo.interests.Interest;
import itboom.com.swap.pojo.interests.InterestsResponse;
import itboom.com.swap.support.NetworkResult;

public interface SelectInterestsMVP {

    public interface View {
        void onSuccess(ArrayList<Interest> interests);
        void onFailure(NetworkResult result);
        void navigateToApp();
        void showProgress();
        void hideProgress();
    }

    public interface Presenter {
        void setView(View view);
        void onViewLoad();
        void submitInterests(ArrayList<String> interests, String userId);
        void rxUnsubscribe();
    }

    public interface Model {
        Single<InterestsResponse> getAllInterests();
        Completable submitInterests(JsonObject object);
    }
}
