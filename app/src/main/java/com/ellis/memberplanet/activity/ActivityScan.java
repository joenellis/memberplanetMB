package com.ellis.memberplanet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ellis.memberplanet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.session.SharedPrefManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joenellis on 18/04/2018.
 */

public class ActivityScan extends AppCompatActivity{
    private TextInputEditText email;

    private Toolbar mToolbar;
    final private String attend = "present";
    private String mID;
    //private String mEvent;
    private String mYearGroup;

    final private String URL="http://28c67797.ngrok.io/memberplanet/APIs/geteventspinner.php";
    private Spinner spinner;
    private  ArrayList<String> YearGroupName;
    Map<Integer, String> Group = new HashMap<>();


    private Button buttonScan;
    private String qr_code;
    private  IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        spinner=findViewById(R.id.event_name);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Scan");
        setSupportActionBar(mToolbar);


        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
//
//        ////Spinner foryear groups
//        EventName =new ArrayList<>();
//        loadSpinnerData(URL);

        buttonScan = findViewById(R.id.buttonScan);

        integrator = new IntentIntegrator(this);

        buttonScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (view == buttonScan) {

                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan QR Code");
                    integrator.setCameraId(0);  // Use a specific camera of the device
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(true);
                    integrator.initiateScan();
                    integrator.initiateScan();

                }

            }


        });

        ////Spinner foryear groups
        YearGroupName=new ArrayList<>();
        loadSpinnerData(URL);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mYearGroup=spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                String s = spinner.getSelectedItem().toString();
                for (Map.Entry<Integer, String> entry : Group.entrySet()) {
                    Integer YearGroupId = entry.getKey();
                    String value = entry.getValue();
                    if (s.matches(value)){
                        Toast.makeText(getApplicationContext(), ""+YearGroupId, Toast.LENGTH_SHORT).show();
                        mID = String.valueOf(YearGroupId);
                        getQrCode();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String mCountrynull = null;
            }
        });

    }

//    private void loadSpinnerData(String url) {
//
//        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try{
//                    JSONObject jsonObject=new JSONObject(response);
//
//                    if(jsonObject.getInt("error")==0){
//
//                        JSONArray jsonArray=jsonObject.getJSONArray("events");
//
//                        for(int i=0;i<jsonArray.length();i++){
//
//                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
//                            String eventid=jsonObject1.getString("eventid");
//                            String eventname=jsonObject1.getString("name");
//                            event.put(Integer.valueOf(eventid), eventname);
//                            EventName.add(eventname);
//
//                        }
//                    }
//                    spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, EventName));
//                }catch (JSONException e){e.printStackTrace();}
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//    }

    private void loadSpinnerData(String url) {

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);

                    if(jsonObject.getInt("error")==0){

                        JSONArray jsonArray=jsonObject.getJSONArray("events");

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String eventid=jsonObject1.getString("eventid");
                            String name=jsonObject1.getString("name");
                            Group.put(Integer.valueOf(eventid), name);
                            YearGroupName.add(name);

                        }
                    }
                    spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, YearGroupName));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    private void getQrCode() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying Event...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.qrcode(mID);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (!response.body().getError()) {
                        qr_code = response.body().getQrocde().toString();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult resultcode = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(data != null) {
            if(resultcode == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this,resultcode.getContents().toString(), Toast.LENGTH_SHORT).show();
                String qrcode= resultcode.getContents();

                if(qrcode.matches(qr_code) || qrcode.length() == qr_code.length()) {
                    Toast.makeText(this,"we made it!", Toast.LENGTH_SHORT).show();
                    Scan();
                }else{
                    Toast.makeText(this,"Wrong QR Code Scanned", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void Scan() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying Event...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String userid = SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getUser_id();

        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.markattendance(userid,attend,mID);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (!response.body().getError()) {
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

}