package itboom.com.swap.add_item.mvp;

import java.util.ArrayList;

import io.reactivex.Single;
import itboom.com.swap.pojo.interests.Interest;
import itboom.com.swap.pojo.interests.InterestsResponse;
import itboom.com.swap.support.NetworkResult;

public interface AddItemMVP  {
    interface View {
        void onInterestsSuccess(ArrayList<Interest> interests);
        void onInterestsError(NetworkResult result);
    }

    interface Presenter {
        void setView(View view);
        void getInterests();
        void rxUnsubscribe();
    }

    interface Model {
        Single<InterestsResponse> getAllInterests();
    }
}
