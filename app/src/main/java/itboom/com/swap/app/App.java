package itboom.com.swap.app;

import android.app.Application;

import itboom.com.swap.add_item.di.AddItemModule;
import itboom.com.swap.auth.interests.di.InterestsModule;
import itboom.com.swap.auth.login.di.LoginModule;
import itboom.com.swap.auth.register.di.RegisterModule;
import itboom.com.swap.home.di.HomeModule;
import itboom.com.swap.swaps.di.SwapsModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .loginModule(new LoginModule())
                .registerModule(new RegisterModule())
                .interestsModule(new InterestsModule())
                .homeModule(new HomeModule())
                .swapsModule(new SwapsModule())
                .addItemModule(new AddItemModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
