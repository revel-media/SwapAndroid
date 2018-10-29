package itboom.com.swap.auth.interests;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.main.AppActivity;
import itboom.com.swap.R;
import itboom.com.swap.app.App;
import itboom.com.swap.auth.interests.mvp.SelectInterestsMVP;
import itboom.com.swap.pojo.interests.Interest;
import itboom.com.swap.support.Constants;
import itboom.com.swap.support.NetworkResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectInterestsFragment extends Fragment implements SelectInterestsMVP.View {

    private static final String TAG = SelectInterestsFragment.class.getName();
    private ArrayList<Interest> interests;

    @BindView(R.id.interestsRecView)
    RecyclerView recyclerView;

    @BindView(R.id.letsStart)
    MaterialButton start;

    @Inject
    SharedPreferences preferences;

    @Inject
    SelectInterestsMVP.Presenter presenter;

    @Inject
    InterestsAdapter adapter;

    ProgressDialog dialog;

    public SelectInterestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_interests, container, false);
        ButterKnife.bind(this, v);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        dialog = new ProgressDialog(getActivity());
        dialog.show();
        presenter.setView(this);
        presenter.onViewLoad();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected() != null && adapter.getSelected().size() > 0){
                    Log.i(TAG, "onClick: ");
                    presenter.submitInterests(adapter.getSelected(), preferences.getString(Constants.USER_ID, null));
                }
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.rxUnsubscribe();
    }

    @Override
    public void onSuccess(ArrayList<Interest> i) {
        dialog.dismiss();
        interests = i;
        adapter.setInterests(interests);
        adapter.setSetContext(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void onFailure(NetworkResult result) {
        dialog.dismiss();
        Log.i(TAG, "onFailure: " + result.name());
    }

    @Override
    public void navigateToApp() {
        startActivity(new Intent(getActivity(), AppActivity.class));
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
}
