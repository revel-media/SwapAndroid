package itboom.com.swap.add_item.mvp;

import io.reactivex.Single;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.pojo.interests.InterestsResponse;

public class AddItemModel implements AddItemMVP.Model {

    SwapAPI api;

    public AddItemModel(SwapAPI api) {
        this.api = api;
    }

    @Override
    public Single<InterestsResponse> getAllInterests() {
        return api.getAllInterests();
    }
}
