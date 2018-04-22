package com.ellis.memberplanet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.session.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMyAccount extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mImage;
    private TextView TextFullName, TextEmail,TextContact, Textdob, TextempStatus, Textprofession, Textoraganisation, Textaddress, Textyeargroup;
    private String FullName;
    private String Contact;
    private String Email;
    private String dob;
    private String employment_status;
    private String profession;
    private String organisation;
    private String address;
    private String image;
    private String yeargroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("My Account");
        setSupportActionBar(mToolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        TextFullName = findViewById(R.id.fullname);
        TextEmail = findViewById(R.id.email);
        TextContact =  findViewById(R.id.number);
        Textdob = findViewById(R.id.dob);
        TextempStatus = findViewById(R.id.employmentStatus);
        Textprofession =  findViewById(R.id.profession);
        Textoraganisation = findViewById(R.id.organisation);
        Textaddress = findViewById(R.id.address);
        Textyeargroup =  findViewById(R.id.yeargroup);
        mImage=  findViewById(R.id.userimg);


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Details...");
        progressDialog.show();


        String userid = SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getUser_id();
        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.userprofile(userid);


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
            progressDialog.dismiss();
                if (response.body() != null) {
                    if (!response.body().getError()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                        FullName = response.body().getObjectUser().getFullname();
                        Contact = response.body().getObjectUser().getContact();
                        Email = response.body().getObjectUser().getEmail();
                        dob = response.body().getObjectUser().getDob();
                        employment_status = response.body().getObjectUser().getEmployment_status();
                        profession = response.body().getObjectUser().getProfession();
                        organisation = response.body().getObjectUser().getOrganisation();
                        address = response.body().getObjectUser().getAddress();
                        yeargroup = response.body().getObjectUser().getYeargroupname();
                        image = response.body().getObjectUser().getImage();

                        TextFullName.setText(FullName);
                        TextEmail.setText(Email);
                        TextContact.setText(Contact);
                        Textdob.setText(dob);
                        TextempStatus.setText(employment_status);
                        Textprofession.setText(profession);
                        Textoraganisation.setText(organisation);
                        Textaddress.setText(address);
                        Textyeargroup.setText(yeargroup);

                        Glide.with(getApplicationContext()).load(image).into(mImage);

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });



        FloatingActionButton fab = findViewById(R.id.myAccount_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ActivityEditProfile.class);
                startActivity(intent);
            }
        });
    }
}
