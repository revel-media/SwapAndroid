package itboom.com.swap.auth.login.mvp;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import itboom.com.swap.pojo.auth.AuthResponse;
import itboom.com.swap.pojo.auth.User;
import itboom.com.swap.support.NetworkResult;

public interface LoginMVP {

    public interface View {
        String getEmail();
        String getPassword();
        void showProgress();
        void hideProgress();
        void onLoginSuccess(User user);
        void onLoginFailure(NetworkResult result);
        void emailError(int message);
        void passwordError(int message);
    }

    public interface Presenter {
        void setView(View view);
        void onLoginBtnClicked();
        void rxUnsubscribe();
    }

    public interface Model {
        Single<AuthResponse> login(JsonObject jsonObject);
    }
}
