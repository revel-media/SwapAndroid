package itboom.com.swap.auth.register.di;

import dagger.Module;
import dagger.Provides;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.auth.register.mvp.RegisterMVP;
import itboom.com.swap.auth.register.mvp.RegisterModel;
import itboom.com.swap.auth.register.mvp.RegisterPresenter;

@Module
public class RegisterModule {

    @Provides
    public RegisterMVP.Model getModel(SwapAPI api){
        return new RegisterModel(api);
    }

    @Provides
    RegisterMVP.Presenter getPresenter(RegisterMVP.Model model){
        return new RegisterPresenter(model);
    }
}
