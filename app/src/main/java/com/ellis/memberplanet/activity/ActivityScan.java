package com.ellis.memberplanet.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by joenellis on 18/04/2018.
 */

public class ActivityScan extends AppCompatActivity {
    private TextInputEditText email, password;
    private Toolbar mToolbar;
    private Spinner spinner;
    private String URL="http://techiesatish.com/demo_api/spinner.php";
    private  ArrayList<String> YearGroupName;
    private Button buttonScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        spinner=findViewById(R.id.country_Name);
//
//        mToolbar = findViewById(R.id.toolbar);
//       // mToolbar.setTitle("Scan");
//        setSupportActionBar(mToolbar);
//
//        assert getSupportActionBar() != null;
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);

        buttonScan = findViewById(R.id.buttonScan);

        buttonScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (view == buttonScan) {
                  //  IntentIntegrator integrator = new IntentIntegrator(new ActivityScan());
//                    integrator.forFragment(HomeFragment.this).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//                    integrator.forFragment(HomeFragment.this).setPrompt("Scan QR Code");
//                    integrator.forFragment(HomeFragment.this).setCameraId(0);  // Use a specific camera of the device
//                    integrator.forFragment(HomeFragment.this).setBeepEnabled(false);
//                    integrator.forFragment(HomeFragment.this).setBarcodeImageEnabled(true);
//                    integrator.forFragment(HomeFragment.this).initiateScan();
                    //integrator.initiateScan();

                }

            }


        });

        //Spinner foryear groups
        YearGroupName=new ArrayList<>();
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // mCountry=spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String mCountrynull = null;
            }
        });
    }

    private void loadSpinnerData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getInt("success")==1){
                        JSONArray jsonArray=jsonObject.getJSONArray("Name");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("Country");
                            //YearGroupName.add(country);
                        }
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, YearGroupName));
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

}