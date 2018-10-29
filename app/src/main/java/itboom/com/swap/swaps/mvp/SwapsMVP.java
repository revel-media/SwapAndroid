package itboom.com.swap.swaps.mvp;

import java.util.ArrayList;

import io.reactivex.Single;
import itboom.com.swap.pojo.home.Item;
import itboom.com.swap.pojo.my_items.MyItemsResponse;
import itboom.com.swap.pojo.swaps.SwapRequest;
import itboom.com.swap.pojo.swaps.SwapsResponse;
import itboom.com.swap.support.NetworkResult;

public interface SwapsMVP {

    interface View {
        void onItemsSuccess(ArrayList<Item> items);
        void onItemsFailure(NetworkResult result);
        void onSwapsSuccess(ArrayList<SwapRequest> requests);
        void onSwapsFailure(NetworkResult result);
    }

    interface Presenter {
        void setView(View view);
        void getItems(String userId);
        void getSwaps(String userId);
        void rxUnsubscribe();
    }

    interface Model {
        Single<MyItemsResponse> getItems(String userId);
        Single<SwapsResponse> getOngoingRequests(String userId);
    }
}
