package com.ellis.memberplanet.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joenellis on 18/05/2018.
 */

public class ObjectMembers {

    @SerializedName("userid")
    private String userid;

    @SerializedName("yeargroupid")
    private String yearGroupId;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;


    public String getUserid() {
        return userid;
    }

    public String getYearGroupId() {
        return yearGroupId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
