package com.ellis.memberplanet.api;

import com.ellis.memberplanet.object.ObjectEvent;
import com.ellis.memberplanet.object.ObjectNewsletter;
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.object.ObjectUser;
import com.ellis.memberplanet.object.Trans;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
    private  ObjectProduct objectProductdetail;

    @SerializedName("newsletters")
    private ArrayList<ObjectNewsletter> objectNewsletters;

    @SerializedName("events")
    private ArrayList<ObjectEvent> objectEvents;

    @SerializedName("members")
    private ArrayList<ObjectUser> objectUsers;

    @SerializedName("products")
    private ArrayList<ObjectProduct> objectProducts;

    @SerializedName("tubers")
    private ArrayList<ObjectProduct> objectProductTubers;

    @SerializedName("fruits")
    private ArrayList<ObjectProduct> objectProductFruits;

    @SerializedName("vegetables")
    private ArrayList<ObjectProduct> objectProductVegetables;

    @SerializedName("grains")
    private ArrayList<ObjectProduct> objectProductGrains;

    @SerializedName("diaryfish")
    private ArrayList<ObjectProduct> objectProductDairyFish;
//
//    @SerializedName("products")
//    private ArrayList<ObjectProduct> objectProducts;
//
//    @SerializedName("products")
//    private ArrayList<ObjectProduct> objectProducts;
//
//    @SerializedName("products")
//    private ArrayList<ObjectProduct> objectProducts;
//
//    @SerializedName("products")
//    private ArrayList<ObjectProduct> objectProducts;


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

    public ArrayList<ObjectProduct> getObjectProducts() {
        return objectProducts;
    }

    public ArrayList<ObjectProduct> getObjectProductTubers() {
        return objectProductTubers;
    }

    public ArrayList<ObjectProduct> getObjectProductFruits() {
        return objectProductFruits;
    }

    public ArrayList<ObjectProduct> getObjectProductVegetables() {
        return objectProductVegetables;
    }

    public ArrayList<ObjectProduct> getObjectProductGrains() {
        return objectProductGrains;
    }

    public ArrayList<ObjectProduct> getObjectProductDairyFish() {
        return objectProductDairyFish;
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

    public ArrayList<ObjectUser> getObjectUsers() {
        return objectUsers;
    }

    public String getQrocde() {
        return qrocde;
    }
}
