package com.ellis.memberplanet.object;

import com.google.gson.annotations.SerializedName;

import java.sql.Struct;

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

    @SerializedName("name")
    private String yeargroupname;

    @SerializedName("fullname")
    private String fullname;

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

    @SerializedName("year")
    private String year;

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

    public String getImage() {
        return image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserid() {
        return userid;
    }

    public String getFullname() {
        return fullname;
    }

    public String getYeargroupname() {
        return yeargroupname;
    }

    public String getYear() {
        return year;
    }
}
