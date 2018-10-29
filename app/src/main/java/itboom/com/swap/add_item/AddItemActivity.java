package itboom.com.swap.add_item;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.R;
import itboom.com.swap.add_item.mvp.AddItemMVP;
import itboom.com.swap.app.App;
import itboom.com.swap.custom_views.SwapButton;
import itboom.com.swap.custom_views.SwapEditText;
import itboom.com.swap.pojo.interests.Interest;
import itboom.com.swap.support.NetworkResult;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import javax.inject.Inject;

public class AddItemActivity extends AppCompatActivity implements AddItemMVP.View {

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.title)
    TextInputEditText title;

    @BindView(R.id.description)
    TextInputEditText description;

    @BindView(R.id.category)
    TextInputEditText category;

    @BindView(R.id.addImage)
    ImageView addImages;

    @BindView(R.id.addItem)
    SwapButton addItem;

    @Inject
    AddItemMVP.Presenter presenter;

    ProgressDialog dialog;
    PopupMenu popupMenu;

    ArrayList<Interest> interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);

        dialog = new ProgressDialog(this);
        showProgress();

        presenter.setView(this);
        presenter.getInterests();

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(AddItemActivity.this, v);
                if (interests != null && interests.size() > 0){
                    Log.i("#NUM#", "onClick: " + interests.size());
                    for (Interest interest : interests) {
                        popupMenu.getMenu().add(interest.getName());
                    }
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public void onInterestsSuccess(ArrayList<Interest> i) {
        hideProgress();
        interests = i;
    }

    @Override
    public void onInterestsError(NetworkResult result) {
        hideProgress();
    }

    private void showProgress(){
        if (!dialog.isShowing()){
            dialog.show();
        }
    }

    private void hideProgress(){
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
