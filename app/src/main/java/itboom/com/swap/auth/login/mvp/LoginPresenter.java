package itboom.com.swap.auth.login.mvp;

import android.util.Patterns;

import com.google.gson.JsonObject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itboom.com.swap.R;
import itboom.com.swap.pojo.auth.AuthResponse;
import itboom.com.swap.support.NetworkResult;

public class LoginPresenter implements LoginMVP.Presenter {

    private static final String TAG = LoginPresenter.class.getName();

    LoginMVP.View view;
    LoginMVP.Model model;
    Disposable disposable;

    public LoginPresenter(LoginMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginMVP.View view) {
        this.view = view;
    }

    @Override
    public void onLoginBtnClicked() {
        if (view != null) {
            if (view.getEmail().isEmpty()){
                view.emailError(R.string.empty_email);
                return;
            }

            if (view.getPassword().isEmpty()){
                view.passwordError(R.string.empty_password);
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(view.getEmail()).matches()){
                view.emailError(R.string.email_error);
                return;
            }

            if (!(view.getPassword().length() >= 6)) {
                view.passwordError(R.string.password_error);
                return;
            }

            view.showProgress();

            JsonObject object = new JsonObject();
            object.addProperty("email", view.getEmail());
            object.addProperty("password", view.getPassword());

            model.login(object)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<AuthResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(AuthResponse authResponse) {
                            view.hideProgress();
                            if (authResponse != null){
                                if (authResponse.getError()) {
                                    view.onLoginFailure(NetworkResult.INVALID_CREDENTIALS);
                                } else {
                                    view.onLoginSuccess(authResponse.getUser());
                                }
                            } else {
                                view.onLoginFailure(NetworkResult.NETWORK_ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideProgress();
                            view.onLoginFailure(NetworkResult.UNEXPECTED_ERROR);
                        }
                    });
        }
    }

    @Override
    public void rxUnsubscribe() {
        if (view != null) {
            if (disposable != null) {
                if (!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
        }
    }
}
