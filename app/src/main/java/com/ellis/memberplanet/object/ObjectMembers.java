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

    @SerializedName("name")
    private String name;

    public ObjectMembers(String userid, String yearGroupId, String firstname, String lastname, String password, String email, String dob, String address, String employmentStatus, String profession, String organisation, String contact, Object token, Object clientReference, Object channel, String image, String createdAt, String name) {
        this.userid = userid;
        this.yearGroupId = yearGroupId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.employmentStatus = employmentStatus;
        this.profession = profession;
        this.organisation = organisation;
        this.contact = contact;
        this.token = token;
        this.clientReference = clientReference;
        this.channel = channel;
        this.image = image;
        this.createdAt = createdAt;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

}
