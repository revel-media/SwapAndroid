package itboom.com.swap.pojo.my_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import itboom.com.swap.pojo.home.Item;

public class MyItemsResponse {
    @Expose
    @SerializedName("error")
    private boolean error;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("items")
    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

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
}
