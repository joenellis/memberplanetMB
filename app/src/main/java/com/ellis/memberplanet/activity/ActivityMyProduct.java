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

public class ActivityMyProduct extends AppCompatActivity {

    ArrayList<String> mImages;
    View mBottomSheet;
    Toolbar mToolbar;
    TextView mPrice;
    Button mMapButton;
    Button mDescButton;
    TextView mCall;
    TextView mLocation;
    TextView mFarmerName;
    TextView mProductName;
    TextView mDescription;
    ImageView mProductImage;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    BottomSheetBehavior mBottomSheetBehavior;
    String productId;
    private String audio;
    private String video;
    private String contact;
    VideoView videoview;
    ProgressDialog pDialog;
    FloatingActionButton mEditFab;
    private boolean isUploader;
    private String location;

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
        productId = intent.getStringExtra("ID");


        final ProgressDialog progressDialog = new ProgressDialog(ActivityMyProduct.this);
        progressDialog.setMessage("Loading Product Details...");
        progressDialog.show();

        /////test to call per product selected
        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.memberdetails(productId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                if (response.body() != null) {
                    if (!response.body().getError()) {
//
//
//                        FullName = response.body().getObjectUser().getFullname();
//                        Contact = response.body().getObjectUser().getContact();
//                        Email = response.body().getObjectUser().getEmail();
//                        dob = response.body().getObjectUser().getDob();
//                        employment_status = response.body().getObjectUser().getEmployment_status();
//                        profession = response.body().getObjectUser().getProfession();
//                        organisation = response.body().getObjectUser().getOrganisation();
//                        address = response.body().getObjectUser().getAddress();
//                        yeargroup = response.body().getObjectUser().getYeargroupname();
//                        image = response.body().getObjectUser().getImage();
//
//                        TextFullName.setText(FullName);
//                        TextEmail.setText(Email);
//                        TextContact.setText(Contact);
//                        Textdob.setText(dob);
//                        TextempStatus.setText(employment_status);
//                        Textprofession.setText(profession);
//                        Textoraganisation.setText(organisation);
//                        Textaddress.setText(address);
//                        Textyeargroup.setText(yeargroup);
//
//                        Glide.with(getApplicationContext()).load(image).into(mImage);

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void initializeComponents() {
        mToolbar = findViewById(R.id.viewProduct_toolbar);
        mRecyclerView = findViewById(R.id.viewProduct_recyclerViewForImages);
//        mDescription = findViewById(R.id.viewProduct_txtDescriptionText);
//        mDescButton = findViewById(R.id.viewProduct_btnMoreDescription);
//        mProductImage = findViewById(R.id.viewProduct_imgProductImage);
//        mProductName = findViewById(R.id.viewProduct_txtProductName);
//        mMapButton = findViewById(R.id.viewProduct_btnViewOnMap);
//        mFarmerName = findViewById(R.id.viewProduct_txtFarmName);
//        mCall = findViewById(R.id.viewProduct_txtCall);
//        mLocation = findViewById(R.id.viewProduct_txtLocation);
//        mPrice = findViewById(R.id.viewProduct_txtPrice);

        layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
    }

    public void onMapButtonClick(View view) {

        String bn = "geo:0,0?q=";

        Uri gmmIntentUri = Uri.parse(bn + location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Snackbar.make(
                    findViewById(R.id.viewProduct_layRoot),
                    "You have no suitable app",
                    Snackbar.LENGTH_LONG
            ).show();
        }


    }

    public void onCallButtonClick(View view) {
        Snackbar.make(findViewById(R.id.viewProduct_layRoot),
                "You Cannot Call Yourself",
                Snackbar.LENGTH_LONG
        ).show();
    }


}
