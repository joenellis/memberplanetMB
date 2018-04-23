package com.ellis.memberplanet.object;

import com.google.gson.annotations.SerializedName;

public class ObjectUser {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("year_group_id")
    private String year_group_id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("contact")
    private String contact;

    @SerializedName("dob")
    private String dob;

    @SerializedName("employment_status")
    private String employment_status;

    @SerializedName("profession")
    private String profession;

    @SerializedName("organisation")
    private String organisation;

    @SerializedName("address")
    private String address;

    public ObjectUser(String user_id, String year_group_id, String firstname, String lastname, String email, String password, String contact, String dob, String employment_status, String profession, String organisation, String address, String image, String yeargroupname) {
        this.user_id = user_id;
        this.year_group_id = year_group_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.dob = dob;
        this.employment_status = employment_status;
        this.profession = profession;
        this.organisation = organisation;
        this.address = address;
        this.image = image;
        this.yeargroupname = yeargroupname;
    }

    @SerializedName("image")
    private String image;

    @SerializedName("yeargroupname")
    private String yeargroupname;

    public ObjectUser() {
    }

//    public ObjectUser(String user_id, String fullname, String email, String password, String contact, String question, String answer) {
//        this.user_id = user_id;
//        this.fullname = fullname;
//        this.email = email;
//        this.password = password;
//        this.contact = contact;
//        this.question = question;
//        this.answer = answer;
//    }


    public ObjectUser(String user_id, String year_group_id, String fullname, String email, String password, String contact, String dob, String employment_status, String profession, String organisation, String address, String image, String yeargroupname) {
        this.user_id = user_id;
        this.year_group_id = year_group_id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.dob = dob;
        this.employment_status = employment_status;
        this.profession = profession;
        this.organisation = organisation;
        this.address = address;
        this.image = image;
        this.yeargroupname = yeargroupname;
    }


    public ObjectUser(String user_id, String yeargroupid, String fullname, String email, String password, String contact, String image) {
        this.user_id = user_id;
        this.year_group_id = yeargroupid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getYeargroupid() {
        return year_group_id;
    }

    public String getDob() {
        return dob;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public String getProfession() {
        return profession;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getYeargroupname() {
        return yeargroupname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }


}
