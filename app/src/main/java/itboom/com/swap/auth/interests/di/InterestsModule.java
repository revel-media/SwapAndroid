package itboom.com.swap.auth.interests.di;

import dagger.Module;
import dagger.Provides;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.auth.interests.InterestsAdapter;
import itboom.com.swap.auth.interests.mvp.InterestsModel;
import itboom.com.swap.auth.interests.mvp.InterestsPresenter;
import itboom.com.swap.auth.interests.mvp.SelectInterestsMVP;

@Module
public class InterestsModule {

    @Provides
    public SelectInterestsMVP.Model getModel(SwapAPI api){
        return new InterestsModel(api);
    }

    @Provides
    public SelectInterestsMVP.Presenter getPresenter(SelectInterestsMVP.Model model){
        return new InterestsPresenter(model);
    }

    @Provides
    public InterestsAdapter getAdapter(){
        return new InterestsAdapter();
    }
}
