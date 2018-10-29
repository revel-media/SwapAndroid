package itboom.com.swap.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.R;
import itboom.com.swap.custom_views.SwapButton;
import itboom.com.swap.custom_views.SwapTextView;
import itboom.com.swap.pojo.home.Item;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

    ArrayList<Item> items;
    Context context;

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_card, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemImage)
        ImageView image;

        @BindView(R.id.itemTitle)
        SwapTextView title;

        @BindView(R.id.itemDetails)
        SwapTextView description;

        @BindView(R.id.by)
        SwapTextView by;

        @BindView(R.id.ask_btn)
        SwapButton askSwap;

        @BindView(R.id.info_btn)
        ImageView info;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Item item){
            title.setText(item.getName());
            description.setText(item.getDescription());
            by.setText(item.getOwner().getName());

            askSwap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
