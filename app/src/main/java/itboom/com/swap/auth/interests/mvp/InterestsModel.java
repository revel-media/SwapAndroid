package itboom.com.swap.auth.interests.mvp;

import com.google.gson.JsonObject;

import io.reactivex.Completable;
import io.reactivex.Single;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.pojo.interests.InterestsResponse;

public class InterestsModel implements SelectInterestsMVP.Model {

    SwapAPI api;

    public InterestsModel(SwapAPI api) {
        this.api = api;
    }

    @Override
    public Single<InterestsResponse> getAllInterests() {
        return api.getAllInterests();
    }

    @Override
    public Completable submitInterests(JsonObject object) {
        return api.submitInterests(object);
    }
}
