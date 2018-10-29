package itboom.com.swap.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import itboom.com.swap.R;
import itboom.com.swap.app.App;
import itboom.com.swap.auth.login.LoginFragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity implements AuthRouting {

    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //inject this context to di
        ((App) getApplication()).getAppComponent().inject(this);

        this.navigateTo(new LoginFragment(), true);
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.authContainer, fragment);

        if (addToBackStack){
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
