package itboom.com.swap.swaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.R;
import itboom.com.swap.app.App;
import itboom.com.swap.custom_views.SwapTextView;
import itboom.com.swap.pojo.home.Item;
import itboom.com.swap.pojo.swaps.SwapRequest;
import itboom.com.swap.support.Constants;
import itboom.com.swap.support.NetworkResult;
import itboom.com.swap.swaps.mvp.SwapsMVP;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import javax.inject.Inject;

public class SwapsActivity extends AppCompatActivity implements SwapsMVP.View {

    private static final String TAG = SwapsActivity.class.getName();

    @BindView(R.id.swapsRecView)
    RecyclerView recyclerView;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.notAvailable)
    SwapTextView notAvailable;

    @Inject
    SwapsMVP.Presenter presenter;

    @Inject
    SharedPreferences preferences;

    @Inject
    ItemsRecyclerAdapter adapter;

    ArrayList<Item> items;

    ArrayList<SwapRequest> requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swaps);

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        /* todo add userId
        5bb9a552ed60350d2475649c
        */
        presenter.getItems(preferences.getString(Constants.USER_ID, null));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().contentEquals(getString(R.string.my_items))){
                    presenter.getItems(preferences.getString(Constants.USER_ID, null));
                } else {
                    presenter.getSwaps(preferences.getString(Constants.USER_ID, null));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (adapter != null) {
                    if (tab.getText().toString().contentEquals(getString(R.string.my_items))) {
                        if (items != null && items.size() > 0){
                            adapter.setItems(items);
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        if (requests != null && requests.size() > 0){

                        }
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemsSuccess(ArrayList<Item> items) {
        progressBar.setVisibility(View.GONE);
        this.items = items;
        adapter.setContext(this);
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemsFailure(NetworkResult result) {
        progressBar.setVisibility(View.GONE);
        Log.i(TAG, "onItemsFailure: " + result.name());
        if (result == NetworkResult.EMPTY_RESULT){
            notAvailable.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSwapsSuccess(ArrayList<SwapRequest> requests) {

    }

    @Override
    public void onSwapsFailure(NetworkResult result) {

    }
}
