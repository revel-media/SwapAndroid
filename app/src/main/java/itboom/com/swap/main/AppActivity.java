package itboom.com.swap.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.R;
import itboom.com.swap.add_item.AddItemActivity;
import itboom.com.swap.app.App;
import itboom.com.swap.app.CurrentPage;
import itboom.com.swap.auth.AuthActivity;
import itboom.com.swap.home.HomeFragment;
import itboom.com.swap.support.Constants;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.transitionseverywhere.TransitionManager;

import javax.inject.Inject;

public class AppActivity extends AppCompatActivity implements View.OnClickListener, HomeFragment.Callback {

    @BindView(R.id.drawerToggle)
    ImageView toggle;

    @BindView(R.id.homeContainer)
    RelativeLayout homeScreen;

    @BindView(R.id.navContainer)
    LinearLayout navigation;

    @BindView(R.id.drawer_layout)
    RelativeLayout viewGroup;

    @BindView(R.id.home)
    LinearLayout home;

    @BindView(R.id.language)
    LinearLayout language;

    @BindView(R.id.profile)
    LinearLayout profile;

    @BindView(R.id.myInterests)
    LinearLayout myInterests;

    @BindView(R.id.about)
    LinearLayout about;

    @BindView(R.id.contact)
    LinearLayout contact;

    @BindView(R.id.logout)
    LinearLayout logout;

    @Inject
    SharedPreferences preferences;

    boolean isShowing = false;
    CurrentPage page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        page = CurrentPage.HOME;

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               toggle();
            }
        });

        home.setOnClickListener(this);
        language.setOnClickListener(this);
        profile.setOnClickListener(this);
        myInterests.setOnClickListener(this);
        about.setOnClickListener(this);
        contact.setOnClickListener(this);
        logout.setOnClickListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new HomeFragment())
                .commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.drawerToggle){
            toggle();
        }

        switch (v.getId()){
            case R.id.home:
                page = CurrentPage.HOME;
                break;
            case R.id.language:
                page = CurrentPage.LANGUAGE;
                break;
            case R.id.myInterests:
                page = CurrentPage.MY_INTERESTS;
                break;
            case R.id.profile:
                page = CurrentPage.PROFILE;
                break;
            case R.id.about:
                page = CurrentPage.ABOUT;
                break;
            case R.id.contact:
                page = CurrentPage.CONTACT;
                break;
            case R.id.logout:
                preferences.edit().putBoolean(Constants.IS_LOGGED_IN, false)
                        .putString(Constants.USER_ID, null)
                        .apply();
                startActivity(new Intent(AppActivity.this, AuthActivity.class));
                finish();
                break;
        }
    }

    void toggle(){
        RelativeLayout.LayoutParams params;
        if (isShowing) {
            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            homeScreen.setLayoutParams(params);
            //homeScreen.setVisibility(View.VISIBLE);
            isShowing = false;
        } else {
            params = new RelativeLayout.LayoutParams(300,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            homeScreen.setLayoutParams(params);
            //homeScreen.setVisibility(View.GONE);
            isShowing = true;
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
        else
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        TransitionManager.beginDelayedTransition(viewGroup);
    }

    private void navigateTo(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onStartAddItem() {
        startActivity(new Intent(this, AddItemActivity.class));
    }
}
