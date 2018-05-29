package com.ellis.memberplanet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityViewMember extends AppCompatActivity {


    Toolbar mToolbar;
    private ImageView mImage;
    private TextView TextFullName, TextEmail,TextContact, TextempStatus, Textprofession1, Textprofession2, Textorganisation, Textaddress, Textyeargroup, Textyear, Textdatejoined;
    private String FullName;
    private String Contact;
    private String Email;
    private String employment_status;
    private String profession1, profession2;
    private String organisation;
    private String address;
    private String image;
    private String year;
    private String yeargroup;
    private String joined;
    RecyclerView.LayoutManager layoutManager;
    String memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);
        initializeComponents();
        
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Member");
        setSupportActionBar(mToolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        Intent intent = getIntent();
        memberId = intent.getStringExtra("ID");


        final ProgressDialog progressDialog = new ProgressDialog(ActivityViewMember.this);
        progressDialog.setMessage("Loading Details...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        /////test to call per product selected
        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.memberdetails(memberId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                if (response.body() != null) {
                    if (!response.body().getError()) {


                        FullName = response.body().getObjectMember().getFullname();
                        Contact = response.body().getObjectMember().getContact();
                        Email = response.body().getObjectMember().getEmail();
                        employment_status = response.body().getObjectMember().getEmploymentStatus();
                        profession1 = response.body().getObjectMember().getProfession();
                        profession2 = response.body().getObjectMember().getProfession();
                        organisation = response.body().getObjectMember().getOrganisation();
                        address = response.body().getObjectMember().getAddress();
                        yeargroup = response.body().getObjectMember().getYeargroupname();
                        image = response.body().getObjectMember().getImage();
                        year = response.body().getObjectMember().getYear();
                        joined = response.body().getObjectMember().getCreatedAt();

                        TextFullName.setText(FullName);
                        TextEmail.setText(Email);
                        TextContact.setText(Contact);
                        TextempStatus.setText(employment_status);
                        Textprofession1.setText(profession1);
                        Textprofession2.setText(profession2);
                        Textorganisation.setText(organisation);
                        Textaddress.setText(address);
                        Textyeargroup.setText(yeargroup);
                        Textyear.setText(year);
                        Textdatejoined.setText(joined);

                        Glide.with(getApplicationContext()).load(image).into(mImage);

                       Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void initializeComponents() {
        mToolbar = findViewById(R.id.viewProduct_toolbar);

        TextFullName = findViewById(R.id.TextFullName);
        TextEmail = findViewById(R.id.TextEmail);
        TextContact =  findViewById(R.id.TextContact);
        TextempStatus = findViewById(R.id.TextempStatus);
        Textprofession1 =  findViewById(R.id.Textprofession1);
        Textprofession2 =  findViewById(R.id.Textprofession2);
        Textorganisation = findViewById(R.id.Textorganisation);
        Textaddress = findViewById(R.id.Textaddress);
        Textyeargroup =  findViewById(R.id.Textyeargroup);
        Textdatejoined = findViewById(R.id.Textdatejoined);

        mImage=  findViewById(R.id.memberimg);
        Textyear =  findViewById(R.id.Textyear);

        layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
    }


    public void onCallButtonClick(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", Contact, null));

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }
    }

    public void onEmailButtonClick(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, Email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "");

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }
    }

    public void onSmsButtonClick(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", Contact, null));

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }
    }


}
