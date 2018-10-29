package itboom.com.swap.auth.login.mvp;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.pojo.auth.AuthResponse;

public class LoginModel implements LoginMVP.Model {

    private SwapAPI api;

    public LoginModel(SwapAPI api) {
        this.api = api;
    }

    @Override
    public Single<AuthResponse> login(JsonObject jsonObject) {
        return api.login(jsonObject);
    }
}
