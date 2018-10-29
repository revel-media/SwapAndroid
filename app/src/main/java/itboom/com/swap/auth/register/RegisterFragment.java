package itboom.com.swap.auth.register;


import android.app.ProgressDialog;
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
import itboom.com.swap.R;
import itboom.com.swap.app.App;
import itboom.com.swap.auth.AuthRouting;
import itboom.com.swap.auth.interests.SelectInterestsFragment;
import itboom.com.swap.auth.register.mvp.RegisterMVP;
import itboom.com.swap.pojo.auth.User;
import itboom.com.swap.support.Constants;
import itboom.com.swap.support.NetworkResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements RegisterMVP.View {

    private static final String TAG = RegisterFragment.class.getName();

    @BindView(R.id.nameText)
    TextInputEditText name;

    @BindView(R.id.emailText)
    TextInputEditText email;

    @BindView(R.id.passwordText)
    TextInputEditText password;

    @BindView(R.id.phoneText)
    TextInputEditText phone;

    @BindView(R.id.register_btn)
    MaterialButton register;

    @BindView(R.id.have_acount)
    TextView haveAccount;

    @Inject
    RegisterMVP.Presenter presenter;

    @Inject
    SharedPreferences preferences;

    ProgressDialog dialog;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        dialog = new ProgressDialog(getActivity());

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((AuthRouting) getActivity()).navigateTo(new LoginFragment(), false);
                getActivity().onBackPressed();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterBtnPressed();
            }
        });


        return view;
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
    public String getName() {
        return name.getText().toString();
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
    public String getPhone() {
        return phone.getText().toString();
    }

    @Override
    public void nameError(int message) {
        name.setError(getString(message));
    }

    @Override
    public void emailError(int message) {
        email.setError(getString(message));
    }

    @Override
    public void passwordError(int message) {
        password.setError(getString(message));
    }

    @Override
    public void phoneError(int message) {
        phone.setError(getString(message));
    }

    @Override
    public void showProgress() {
        if (!dialog.isShowing()){
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
    public void registerSuccess(User user) {
        preferences.edit().putBoolean(Constants.IS_LOGGED_IN, true)
                .putString(Constants.USER_ID, user.getId())
                .apply();

        ((AuthRouting) getActivity()).navigateTo(new SelectInterestsFragment(), false);
    }

    @Override
    public void registerFailure(NetworkResult result) {
        Log.i(TAG, "registerFailure: " + result.name());
    }
}
