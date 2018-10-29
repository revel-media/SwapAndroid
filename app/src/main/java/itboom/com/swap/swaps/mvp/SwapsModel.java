package itboom.com.swap.swaps.mvp;

import io.reactivex.Single;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.pojo.my_items.MyItemsResponse;
import itboom.com.swap.pojo.swaps.SwapsResponse;

public class SwapsModel implements SwapsMVP.Model {

    SwapAPI api;

    public SwapsModel(SwapAPI api) {
        this.api = api;
    }

    @Override
    public Single<MyItemsResponse> getItems(String userId) {
        return api.getMyItems(userId);
    }

    @Override
    public Single<SwapsResponse> getOngoingRequests(String userId) {
        return api.getOngoingSwaps(userId);
    }
}
