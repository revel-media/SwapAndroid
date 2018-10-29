package itboom.com.swap.swaps.di;

import dagger.Module;
import dagger.Provides;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.swaps.ItemsRecyclerAdapter;
import itboom.com.swap.swaps.mvp.SwapsMVP;
import itboom.com.swap.swaps.mvp.SwapsModel;
import itboom.com.swap.swaps.mvp.SwapsPresenter;

@Module
public class SwapsModule {
    @Provides
    public SwapsMVP.Model getModel(SwapAPI api){
        return new SwapsModel(api);
    }

    @Provides
    public SwapsMVP.Presenter getPresenter(SwapsMVP.Model model){
        return new SwapsPresenter(model);
    }

    @Provides
    public ItemsRecyclerAdapter getAdapter(){
        return new ItemsRecyclerAdapter();
    }
}
