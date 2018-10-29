package itboom.com.swap.home.mvp;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Single;
import itboom.com.swap.pojo.home.HomeResponse;
import itboom.com.swap.pojo.home.Item;
import itboom.com.swap.support.NetworkResult;

public interface HomeMVP {

    interface View {
        void onLoadSuccess(ArrayList<Item> items);
        void onLoadFailure(NetworkResult result, String message);
        void onAskingSuccess();
        void onAskingFailure(NetworkResult result, String message);
    }

    interface Presenter {
        void setView(View view);
        void onLoad(String userId);
        void onAskForSwap(JsonObject object);
        void onDetails(String id);
        void rxUnsubscribe();
    }

    interface Model {
        Single<HomeResponse> getHomeData(String userId, String paging);
    }
}
