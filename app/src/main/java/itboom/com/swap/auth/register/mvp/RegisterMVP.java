package itboom.com.swap.auth.register.mvp;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import itboom.com.swap.pojo.auth.AuthResponse;
import itboom.com.swap.pojo.auth.User;
import itboom.com.swap.support.NetworkResult;

public interface RegisterMVP {
    public interface View {
        String getName();
        String getEmail();
        String getPassword();
        String getPhone();
        void nameError(int message);
        void emailError(int message);
        void passwordError(int message);
        void phoneError(int message);
        void showProgress();
        void hideProgress();
        void registerSuccess(User user);
        void registerFailure(NetworkResult result);
    }

    public interface Presenter {
        void setView(View view);
        void onRegisterBtnPressed();
        void rxUnsubscribe();
    }

    public interface Model {
        Single<AuthResponse> register(JsonObject object);
    }
}
