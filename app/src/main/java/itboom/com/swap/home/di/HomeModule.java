package itboom.com.swap.home.di;

import dagger.Module;
import dagger.Provides;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.home.mvp.HomeMVP;
import itboom.com.swap.home.mvp.HomeModel;
import itboom.com.swap.home.mvp.HomePresenter;
import itboom.com.swap.home.HomeRecyclerAdapter;

@Module
public class HomeModule {
    @Provides
    public HomeMVP.Presenter getPresenter(HomeMVP.Model model){
        return new HomePresenter(model);
    }

    @Provides
    public HomeMVP.Model getModel(SwapAPI api){
        return new HomeModel(api);
    }

    @Provides
    public HomeRecyclerAdapter getAdapter(){
        return new HomeRecyclerAdapter();
    }
}
