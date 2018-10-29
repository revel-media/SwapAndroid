package itboom.com.swap.pojo.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import itboom.com.swap.pojo.auth.User;

public class Item {
    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("owner")
    private User owner;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("category")
    private String category;

    @Expose
    @SerializedName("images")
    private Image[] images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }
}
