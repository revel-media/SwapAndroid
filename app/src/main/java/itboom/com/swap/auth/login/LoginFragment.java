package itboom.com.swap.auth.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.main.AppActivity;
import itboom.com.swap.R;
import itboom.com.swap.app.App;
import itboom.com.swap.auth.AuthRouting;
import itboom.com.swap.auth.register.RegisterFragment;
import itboom.com.swap.auth.login.mvp.LoginMVP;
import itboom.com.swap.pojo.auth.User;
import itboom.com.swap.support.Constants;
import itboom.com.swap.support.NetworkResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginMVP.View {

    private static final String TAG = LoginFragment.class.getName();

    ProgressDialog dialog;

    @Inject
    LoginMVP.Presenter presenter;

    @Inject
    SharedPreferences preferences;

    @BindView(R.id.emailText)
    TextInputEditText email;

    @BindView(R.id.password_edit_text)
    TextInputEditText password;

    @BindView(R.id.login_btn)
    MaterialButton login;

    @BindView(R.id.have_no_account)
    TextView haveNoAccount;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        dialog = new ProgressDialog(getActivity());

        haveNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthRouting)getActivity()).navigateTo(new RegisterFragment(), false);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginBtnClicked();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.rxUnsubscribe();
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showProgress() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void onLoginSuccess(User user) {
        preferences.edit().putBoolean(Constants.IS_LOGGED_IN, true)
                .putString(Constants.USER_ID, user.getId())
                .apply();
        startActivity(new Intent(getActivity(), AppActivity.class));
    }

    @Override
    public void onLoginFailure(NetworkResult result) {
        Log.i(TAG, "onLoginFailure: " + result.name());
    }

    @Override
    public void emailError(int message) {
        email.setError(getString(message));
    }

    @Override
    public void passwordError(int message) {
        password.setError(getString(message));
    }
}
