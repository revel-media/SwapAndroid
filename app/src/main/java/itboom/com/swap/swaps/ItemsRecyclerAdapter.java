package itboom.com.swap.swaps;

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

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ItemsViewHolder> {
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
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.swaps_card, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemImage)
        ImageView image;

        @BindView(R.id.itemTitle)
        SwapTextView title;

        @BindView(R.id.itemDetails)
        SwapTextView description;

        @BindView(R.id.status)
        SwapTextView status;

        @BindView(R.id.date)
        SwapTextView data;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Item item){
            title.setText(item.getName());
            description.setText(item.getDescription());
            status.setText(context.getString(R.string.status) + " "  + item.getStatus());
            data.setText(item.getCreatedAt());
        }
    }
}
