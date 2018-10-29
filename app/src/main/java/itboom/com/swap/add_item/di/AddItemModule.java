package itboom.com.swap.add_item.di;

import dagger.Module;
import dagger.Provides;
import itboom.com.swap.add_item.mvp.AddItemMVP;
import itboom.com.swap.add_item.mvp.AddItemModel;
import itboom.com.swap.add_item.mvp.AddItemPresenter;
import itboom.com.swap.app.SwapAPI;

@Module
public class AddItemModule {
    @Provides
    public AddItemMVP.Model getModel(SwapAPI api){
        return new AddItemModel(api);
    }

    @Provides
    public AddItemMVP.Presenter getPresenter(AddItemMVP.Model model){
        return new AddItemPresenter(model);
    }
}
