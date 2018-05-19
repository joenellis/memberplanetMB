package com.ellis.memberplanet.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiCall {

    @GET("login.php")
    Call<Result> userLogin(@Query("email") String email,
                           @Query("password") String password) ;

    @GET("verifyemail.php")
    Call<Result> verifyemail(@Query("email") String email);

    @GET("getallproducts.php")
    Call<Result> products(@Query("key") String key) ;

    @GET("getnewsletter.php")
    Call<Result> newsletter();

    @GET("getevent.php")
    Call<Result> event();

    @GET("getmymembers.php")
    Call<Result> mymembers(@Query("yeargroupid") String yeargroupid);

    @GET("getprofile.php")
    Call<Result> userprofile(@Query("userid") String userid);

    @GET("getqrcode.php")
    Call<Result> qrcode(@Query("eventid") String mID);

    @GET("attendance.php")
    Call<Result> markattendance(@Query("userid") String userid,
                                @Query("attendance") String attend,
                                @Query("mID") String mID);

    @GET("getcategoryproducts.php")
    Call<Result> productcaegory(@Query("categoryid") String categoryid);

    @GET("getproductdetail.php")
    Call<Result> productdetails(@Query("productid") String productid);

    @GET("mine.php")
    Call<Result> userPay(@Query("name") String fullname,
                         @Query("email") String email,
                         @Query("number") String number,
                         @Query("channel") String channel,
                         @Query("amount") String amount,
                         @Query("token") String token,
                         @Query("clientreference") String clientreference,
                         @Query("accountid") String mID,
                         @Query("yeargroupid") String yeargroupid,
                         @Query("userid") String userid);

    @GET("transaction.php")
    Call<Result> userTransaction(@Query("userid") String userid);

    @GET("getyeargroup.php")
    Call<Result> category();

    @FormUrlEncoded
    @POST("userupdate.php")
    Call<Result> userUpdate(@Field("userid") String id,
                            @Field("fullname") String fullname,
                            @Field("email") String email,
                            @Field("contact") String contact);

    @GET("authenticatecode.php")
    Call<Result> authenticate( @Query("id") String yearGroupId,
                               @Query("code") String mYearGroup);

    @FormUrlEncoded
    @POST("signup.php")
    Call<Result> userSignup(@Field("firstname") String mFirstName,
                            @Field("lastname") String mLastName,
                            @Field("number") String mNumber,
                            @Field("email") String mEmail,
                            @Field("password") String mPassword,
                            @Field("dob") String mDob,
                            @Field("yeargroup") String mYearGroup,
                            @Field("address") String mLocation,
                            @Field("profession") String mProfession,
                            @Field("organisation") String mOrganisation,
                            @Field("empStatus") String mStatus);

    ///////////update
    @Multipart
    @POST("userupdateproduct.php")
    Call<Result> updateProduct( @Part("productid") RequestBody id,
                                @Part("categoryid") RequestBody categoryid,
                                @Part("productname") RequestBody productname,
                                @Part("price") RequestBody price,
                                @Part("description") RequestBody description,
                                @Part("location") RequestBody location,
                                @Part MultipartBody.Part file1,
                                @Part MultipartBody.Part file2,
                                @Part MultipartBody.Part file3,
                                @Part MultipartBody.Part file4);

}


