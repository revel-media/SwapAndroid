package itboom.com.swap.auth.login.di;

import dagger.Module;
import dagger.Provides;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.auth.login.mvp.LoginMVP;
import itboom.com.swap.auth.login.mvp.LoginModel;
import itboom.com.swap.auth.login.mvp.LoginPresenter;

@Module
public class LoginModule {

    @Provides
    public LoginMVP.Presenter getPresenter(LoginMVP.Model model){
        return new LoginPresenter(model);
    }

    @Provides
    public LoginMVP.Model getModel(SwapAPI api){
        return new LoginModel(api);
    }
}
