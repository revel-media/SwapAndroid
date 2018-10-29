package itboom.com.swap.pojo.interests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InterestsResponse {
    @Expose
    @SerializedName("error")
    private boolean error;

    @Expose
    @SerializedName("interests")
    private Interest[] interests;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Interest[] getInterests() {
        return interests;
    }

    public void setInterests(Interest[] interests) {
        this.interests = interests;
    }
}
