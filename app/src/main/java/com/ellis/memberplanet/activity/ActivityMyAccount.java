package com.ellis.memberplanet.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.session.SharedPrefManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        progressDialog.setCancelable(false);


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


//    private void showImageDialog() {
//        int dialogTitle = R.string.showImageDialog_title;
//        int dialogOptions = R.array.showImageDialog_dialogOptions;
//
//        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
//
//        imageDialog.setTitle(dialogTitle);
//        imageDialog.setItems(dialogOptions, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int selectedOptionPosition) {
//                switch (selectedOptionPosition) {
//                    case 0:
//                        selectImageFromGallery();
//                        break;
//                    case 1:
//                        selectImageFromCamera();
//                        break;
//                }
//            }
//        });
//        imageDialog.show();
//    }
//
//    private void selectImageFromGallery() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        if (galleryIntent.resolveActivity(getPackageManager()) != null) {
//
//            startActivityForResult(galleryIntent, IMAGE_GALLERY_CODE);
//
//        } else {
//
////            Snackbar.make(
////                    mRootLayout,
////                    R.string.selectImageFromGallery_errorMessage,
////                    Snackbar.LENGTH_LONG
////            ).show();
//
//        }
//    }
//
//    private void selectImageFromCamera() {
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            requestCameraPermission();
//
//        } else {
//
//            takePicture();
//        }
//    }
//
//    private void takePicture() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//
//            // Create the File where the photo should go
//            File pictureFile = null;
//
//            try {
//
//                pictureFile = createImageFile();
//
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//
//            }
//
//            // Continue only if the File was successfully created
//            if (pictureFile != null) {
//
//                mCurrentPhotoUri = FileProvider.getUriForFile(this,
//                        "com.turkeytech.egranja.fileprovider",
//                        pictureFile);
//
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoUri);
//                startActivityForResult(takePictureIntent, IMAGE_CAMERA_CODE);
//            }
//
//        }
//    }
//
//    private void requestCameraPermission() {
//        // Should we show an explanation?
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.CAMERA)) {
//
//            // Show an explanation to the user *asynchronously* -- don't block
//            // this thread waiting for the user's response! After the user
//            // sees the explanation, try again to request the permission.
//
//            Snackbar.make(
//                    mRootLayout,
//                    "This is to let the app take photos and record videos.",
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction("Grant Permission", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ActivityCompat.requestPermissions(AddProductActivity.this,
//                                    new String[]{Manifest.permission.CAMERA},
//                                    MY_PERMISSIONS_REQUEST_CAMERA);
//                        }
//                    })
//                    .show();
//
//        } else {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
//                    MY_PERMISSIONS_REQUEST_CAMERA);
//
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK).format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//
//        return File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",   /* suffix */
//                storageDir      /* directory */
//        );
//    }
//
//    private void showConfirmImagePopup(final Uri imageUri) {
//        AlertDialog.Builder imagePopup = new AlertDialog.Builder(this);
//
//        @SuppressLint("InflateParams")
//        View view = getLayoutInflater().inflate(R.layout.dialog_image_preview, null);
//
//        ImageView imageView = view.findViewById(R.id.imagePreview_image);
//        imageView.setImageURI(imageUri);
//
//        imagePopup.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                setImage(imageUri);
//            }
//        });
//
//        imagePopup.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                removeImage();
//
//                Snackbar.make(mRootLayout, "Image Discarded!", Snackbar.LENGTH_LONG)
//                        .setAction("Undo", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                setImage(imageUri);
//                            }
//                        }).show();
//            }
//        });
//
//        imagePopup.setTitle("Image Preview");
//        imagePopup.setView(view);
//        imagePopup.show();
//    }
//
//
//    private void removeImage() {
//        switch (mCurrentImageButtonId) {
//            case R.id.addProduct_btnAddImage1:
//                mImageButton1.setImageResource(R.drawable.ic_add_photo);
//                mImageFileUri1 = null;
//                break;
//
//            case R.id.addProduct_btnAddImage2:
//                mImageButton2.setImageResource(R.drawable.ic_add_photo);
//                mImageFileUri2 = null;
//                break;
//
//            case R.id.addProduct_btnAddImage3:
//                mImageButton3.setImageResource(R.drawable.ic_add_photo);
//                mImageFileUri3 = null;
//                break;
//
//            case R.id.addProduct_btnAddImage4:
//                mImageButton4.setImageResource(R.drawable.ic_add_photo);
//                mImageFileUri4 = null;
//                break;
//        }
//}
}
