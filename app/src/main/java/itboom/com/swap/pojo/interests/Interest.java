package itboom.com.swap.pojo.interests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Interest {
    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("image")
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
