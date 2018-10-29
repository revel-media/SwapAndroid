package itboom.com.swap.pojo.swaps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import itboom.com.swap.pojo.auth.User;
import itboom.com.swap.pojo.home.Item;

public class SwapRequest {
    @Expose
    @SerializedName("needy")
    private User needy;

    @Expose
    @SerializedName("owner")
    private User owner;

    @Expose
    @SerializedName("providedItem")
    private Item providedItem;

    @Expose
    @SerializedName("neededItem")
    private Item neededItem;

    public User getNeedy() {
        return needy;
    }

    public void setNeedy(User needy) {
        this.needy = needy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Item getProvidedItem() {
        return providedItem;
    }

    public void setProvidedItem(Item providedItem) {
        this.providedItem = providedItem;
    }

    public Item getNeededItem() {
        return neededItem;
    }

    public void setNeededItem(Item neededItem) {
        this.neededItem = neededItem;
    }
}
