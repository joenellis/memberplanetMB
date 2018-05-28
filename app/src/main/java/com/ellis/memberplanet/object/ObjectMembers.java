package com.ellis.memberplanet.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joenellis on 18/05/2018.
 */

public class ObjectMembers {

    @SerializedName("userid")
    private String userid;

    @SerializedName("year_group_id")
    private String yearGroupId;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("dob")
    private String dob;

    @SerializedName("address")
    private String address;

    @SerializedName("employment_status")
    private String employmentStatus;

    @SerializedName("profession")
    private String profession;

    @SerializedName("organisation")
    private String organisation;

    @SerializedName("contact")
    private String contact;

    @SerializedName("Token")
    private Object token;

    @SerializedName("client_reference")
    private Object clientReference;

    @SerializedName("channel")
    private Object channel;

    @SerializedName("image")
    private String image;

    @SerializedName("createdAt")
    private String createdAt;

    public String getYearGroupId() {
        return yearGroupId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getProfession() {
        return profession;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getContact() {
        return contact;
    }

    public Object getToken() {
        return token;
    }

    public Object getClientReference() {
        return clientReference;
    }

    public Object getChannel() {
        return channel;
    }

    public String getImage() {
        return image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserid() {
        return userid;
    }

}
