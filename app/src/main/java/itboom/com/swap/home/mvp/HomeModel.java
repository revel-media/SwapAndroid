package itboom.com.swap.home.mvp;

import io.reactivex.Single;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.pojo.home.HomeResponse;

public class HomeModel implements HomeMVP.Model {

    SwapAPI api;

    public HomeModel(SwapAPI api) {
        this.api = api;
    }

    @Override
    public Single<HomeResponse> getHomeData(String userId, String paging) {
        return api.getHome(userId);
    }
}
