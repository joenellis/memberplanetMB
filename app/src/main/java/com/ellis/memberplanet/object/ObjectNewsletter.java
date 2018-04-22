package com.ellis.memberplanet.object;

/**
 * Created by joenellis on 22/04/2018.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ObjectNewsletter {

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    @SerializedName("publishdate")
    private String publishdate;

    @SerializedName("photo")
    private String photo;

    @SerializedName("fullname")
    private String fullname;

    public ObjectNewsletter(String title, String image, String description, String publishdate, String photo, String fullname) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.publishdate = publishdate;
        this.photo = photo;
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFullname() {
        return fullname;
    }
}
