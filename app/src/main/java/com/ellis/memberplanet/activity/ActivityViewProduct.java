package com.ellis.memberplanet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ellis.memberplanet.R;
import com.ellis.memberplanet.adapter.AdapterViewProductImages;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityViewProduct extends AppCompatActivity {

    Toolbar mToolbar;
    TextView mPrice;
    TextView mCall;
    TextView mLocation;
    TextView mFarmerName;
    TextView mProductName;
    TextView mDescription;
    ImageView mProductImage;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    private String contact;
    private String productName;
    private String productImage;
    private String productDescription;
    private String farmerName;
    private String productPrice;
    private String productLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        initializeComponents();

        setSupportActionBar(mToolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        Intent intent = getIntent();
        String productId = intent.getStringExtra("ID");

        mRecyclerView.setLayoutManager(layoutManager);


        final ProgressDialog progressDialog = new ProgressDialog(ActivityViewProduct.this);
        progressDialog.show();


        /////test to call per product selected
        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.memberdetails(productId);

        call.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {

                progressDialog.dismiss();

                if (response.body() != null) {

                    if (!response.body().getError()) {

                        productName = response.body().getObjectProductdetail().getProductname();
                        productImage = response.body().getObjectProductdetail().getImage();
                        productDescription = response.body().getObjectProductdetail().getDescription();
                        farmerName = response.body().getObjectProductdetail().getFullname();
                        productPrice = response.body().getObjectProductdetail().getPrice();
                        productLocation = response.body().getObjectProductdetail().getLocation();
                        contact = response.body().getObjectProductdetail().getContact();

                        mProductName.setText(productName);
                        Glide.with(getApplicationContext()).load(productImage).into(mProductImage);
                        mDescription.setText(productDescription);
                        mFarmerName.setText(farmerName);
                        mPrice.setText(productPrice);
                        mLocation.setText(productLocation);
                        mCall.setText(contact);

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initializeComponents() {

        mToolbar = findViewById(R.id.viewProduct_toolbar);
        mRecyclerView = findViewById(R.id.viewProduct_recyclerViewForImages);
        mDescription = findViewById(R.id.viewProduct_txtDescriptionText);
        mProductImage = findViewById(R.id.viewProduct_imgProductImage);
        mProductName = findViewById(R.id.viewProduct_txtProductName);
        mFarmerName = findViewById(R.id.viewProduct_txtFarmName);
        mCall = findViewById(R.id.viewProduct_txtCall);
        mLocation = findViewById(R.id.viewProduct_txtLocation);
        mPrice = findViewById(R.id.viewProduct_txtPrice);

        layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );

    }


    public void onCallButtonClick(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contact, null));

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }

    }

}
