package com.ellis.memberplanet.object;

/**
 * Created by joenellis on 22/04/2018.
 */
import com.google.gson.annotations.SerializedName;

public class ObjectEvent {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("description")
    private String description;

    @SerializedName("date")
    private String date;

    @SerializedName("startdate")
    private String startdate;

    @SerializedName("enddate")
    private String endate;

    @SerializedName("image")
    private String image;

    @SerializedName("qrcode")
    private String qrcode;

    public ObjectEvent(String name, String type, String description, String date, String startdate, String endate, String image, String qrcode) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
        this.startdate = startdate;
        this.endate = endate;
        this.image = image;
        this.qrcode = qrcode;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEndate() {
        return endate;
    }

    public String getImage() {
        return image;
    }

    public String getQrcode() {
        return qrcode;
    }
}
