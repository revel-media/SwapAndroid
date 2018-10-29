package itboom.com.swap.auth.register.mvp;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import itboom.com.swap.app.SwapAPI;
import itboom.com.swap.pojo.auth.AuthResponse;

public class RegisterModel implements RegisterMVP.Model {

    SwapAPI api;

    public RegisterModel(SwapAPI api) {
        this.api = api;
    }

    @Override
    public Single<AuthResponse> register(JsonObject object) {
        return api.register(object);
    }
}
