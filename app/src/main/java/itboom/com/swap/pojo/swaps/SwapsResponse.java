package itboom.com.swap.pojo.swaps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SwapsResponse {
    @Expose
    @SerializedName("error")
    private boolean error;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("requests")
    private SwapRequest[] swaps;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SwapRequest[] getSwaps() {
        return swaps;
    }

    public void setSwaps(SwapRequest[] swaps) {
        this.swaps = swaps;
    }
}
