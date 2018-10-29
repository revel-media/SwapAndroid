package itboom.com.swap.auth.interests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import itboom.com.swap.R;
import itboom.com.swap.pojo.interests.Interest;

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.InterestHolder> {

    private ArrayList<Interest> interests;
    private Context context;
    private ArrayList<String> selected;

    public void setInterests(ArrayList<Interest> interests) {
        selected = new ArrayList<>();
        this.interests = interests;
    }

    public void setSetContext(Context context) {
        this.context = context;
    }

    public ArrayList<String> getSelected() {
        return selected;
    }

    @NonNull
    @Override
    public InterestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.interest_card, parent, false);
        return new InterestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestHolder holder, int position) {
        holder.bind(interests.get(position));
    }

    @Override
    public int getItemCount() {
        return interests.size();
    }

    class InterestHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interestImage)
        ImageView image;

        @BindView(R.id.interestName)
        TextView name;

        @BindView(R.id.select)
        CheckBox select;

        public InterestHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Interest interest){
            name.setText(interest.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (select.isChecked()){
                        select.setChecked(false);
                        selected.remove(interest.getId());
                    } else {
                        select.setChecked(true);
                        if (!selected.contains(interest.getId())){
                            selected.add(interest.getId());
                        }
                    }
                }
            });
        }
    }
}
