package itboom.com.swap.auth.register.mvp;

import android.util.Log;
import android.util.Patterns;

import com.google.gson.JsonObject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itboom.com.swap.R;
import itboom.com.swap.pojo.auth.AuthResponse;
import itboom.com.swap.support.NetworkResult;

public class RegisterPresenter implements RegisterMVP.Presenter {

    RegisterMVP.View view;
    RegisterMVP.Model model;
    Disposable disposable;

    public RegisterPresenter(RegisterMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(RegisterMVP.View view) {
        this.view = view;
    }

    @Override
    public void onRegisterBtnPressed() {
        if (view != null){
            if (view.getName().isEmpty()){
                view.nameError(R.string.empty_name);
                return;
            }

            if (view.getEmail().isEmpty()){
                view.emailError(R.string.empty_email);
                return;
            }

            if (view.getPassword().isEmpty()){
                view.passwordError(R.string.empty_password);
                return;
            }

            if (view.getPhone().isEmpty()){
                view.phoneError(R.string.empty_phone);
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(view.getEmail()).matches()){
                view.emailError(R.string.email_error);
                return;
            }

            if (!Patterns.PHONE.matcher(view.getPhone()).matches()){
                view.phoneError(R.string.phone_error);
                return;
            }

            JsonObject object = new JsonObject();
            object.addProperty("name", view.getName());
            object.addProperty("password", view.getPassword());
            object.addProperty("email", view.getEmail());
            object.addProperty("phone", view.getPhone());

            model.register(object)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<AuthResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(AuthResponse authResponse) {
                            if (authResponse != null){
                                if (authResponse.getError()){
                                    view.registerFailure(NetworkResult.INVALID_CREDENTIALS);
                                } else {
                                    view.registerSuccess(authResponse.getUser());
                                }
                            } else {
                                view.registerFailure(NetworkResult.NETWORK_ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("ERROR_RESULT", "onError: " + e);
                            view.registerFailure(NetworkResult.UNEXPECTED_ERROR);
                        }
                    });
        }
    }

    @Override
    public void rxUnsubscribe() {
        if (view != null && disposable != null){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
