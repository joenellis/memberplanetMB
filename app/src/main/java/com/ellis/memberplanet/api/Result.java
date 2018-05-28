package com.ellis.memberplanet.api;

import com.ellis.memberplanet.object.ObjectEvent;
import com.ellis.memberplanet.object.ObjectNewsletter;
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.object.ObjectUser;
import com.ellis.memberplanet.object.ObjectMembers;
import com.ellis.memberplanet.object.ObjectYearGroup;
import com.ellis.memberplanet.object.Trans;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("qrcode")
    private String qrocde;

    @SerializedName("user")
    private ObjectUser objectUser;

    @SerializedName("transaction")
    private ArrayList<Trans> trans;

    @SerializedName("product")
    private ObjectProduct objectProductdetail;

    @SerializedName("newsletters")
    private ArrayList<ObjectNewsletter> objectNewsletters;

    @SerializedName("events")
    private ArrayList<ObjectEvent> objectEvents;

    @SerializedName("members")
    private ArrayList<ObjectMembers> objectMembers;

    @SerializedName("yeargroup")
    private Map<String, ArrayList<ObjectMembers>> yearGroupMap;

    public Result(Boolean error, String message, ObjectUser objectUser) {
        this.error = error;
        this.message = message;
        this.objectUser = objectUser;
    }

    public Result(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public Result(Boolean error, String message, ArrayList<Trans> trans) {
        this.error = error;
        this.message = message;
        this.trans = trans;
    }
    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ObjectUser getObjectUser() {
        return objectUser;
    }
    public ObjectProduct getObjectProductdetail() {
        return objectProductdetail;
    }

    public ArrayList<Trans> getTrans() {
        return trans;
    }

    public ArrayList<ObjectNewsletter> getObjectNewsletters() {
        return objectNewsletters;
    }

    public ArrayList<ObjectEvent> getObjectEvents() {
    return objectEvents;
}

    public String getQrocde() {
        return qrocde;
    }

    public ArrayList<ObjectMembers> getObjectMembers() {
        return objectMembers;
    }

    public Map<String, ArrayList<ObjectMembers>> getYearGroupMap() {
        return yearGroupMap;
    }

}
