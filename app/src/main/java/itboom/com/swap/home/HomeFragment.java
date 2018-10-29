package itboom.com.swap.home;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.R;
import itboom.com.swap.app.App;
import itboom.com.swap.custom_views.SwapTextView;
import itboom.com.swap.home.mvp.HomeMVP;
import itboom.com.swap.pojo.home.Item;
import itboom.com.swap.support.Constants;
import itboom.com.swap.support.NetworkResult;
import itboom.com.swap.swaps.SwapsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeMVP.View {

    private static final String TAG = HomeFragment.class.getName();

    @BindView(R.id.homeRecView)
    RecyclerView recyclerView;

    @BindView(R.id.homeResult)
    SwapTextView resultNum;

    @BindView(R.id.swaps)
    ImageView swapsBtn;

    @BindView(R.id.addItem)
    ImageView addItemBtn;

    @Inject
    HomeMVP.Presenter presenter;

    @Inject
    SharedPreferences preferences;

    @Inject
    HomeRecyclerAdapter adapter;

    private Callback callback;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        presenter.setView(this);
        presenter.onLoad(preferences.getString(Constants.USER_ID, null));

        adapter.setContext(getActivity());

        swapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SwapsActivity.class));
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onStartAddItem();
            }
        });

        return view;
    }

    @Override
    public void onLoadSuccess(ArrayList<Item> items) {
        adapter.setItems(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        if (items.size() > 1){
            resultNum.setText(items.size() + " " + getString(R.string.results));
        } else {
            resultNum.setText(items.size() + " " + getString(R.string.result));
        }
    }

    @Override
    public void onLoadFailure(NetworkResult result, String message) {
        Log.i(TAG, "onLoadFailure: " + result.name());
    }

    @Override
    public void onAskingSuccess() {

    }

    @Override
    public void onAskingFailure(NetworkResult result, String message) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (callback != null){
            callback = null;
        }
    }

    public interface Callback {
        void onStartAddItem();
    }
}
