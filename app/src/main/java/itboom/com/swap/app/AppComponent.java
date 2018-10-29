package itboom.com.swap.app;

import javax.inject.Singleton;

import dagger.Component;
import itboom.com.swap.add_item.AddItemActivity;
import itboom.com.swap.add_item.di.AddItemModule;
import itboom.com.swap.main.AppActivity;
import itboom.com.swap.auth.AuthActivity;
import itboom.com.swap.auth.interests.SelectInterestsFragment;
import itboom.com.swap.auth.interests.di.InterestsModule;
import itboom.com.swap.auth.register.RegisterFragment;
import itboom.com.swap.auth.login.LoginFragment;
import itboom.com.swap.auth.login.di.LoginModule;
import itboom.com.swap.auth.register.di.RegisterModule;
import itboom.com.swap.home.HomeFragment;
import itboom.com.swap.home.di.HomeModule;
import itboom.com.swap.swaps.SwapsActivity;
import itboom.com.swap.swaps.di.SwapsModule;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, LoginModule.class, RegisterModule.class, InterestsModule.class,
        HomeModule.class, SwapsModule.class, AddItemModule.class})
public interface AppComponent {
    void inject(AuthActivity activity);
    void inject(AppActivity activity);
    void inject(LoginFragment fragment);
    void inject(RegisterFragment fragment);
    void inject(SelectInterestsFragment fragment);
    void inject(HomeFragment fragment);
    void inject(SwapsActivity activity);
    void inject(AddItemActivity activity);
}
