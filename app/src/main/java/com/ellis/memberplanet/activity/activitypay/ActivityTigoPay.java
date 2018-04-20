package com.ellis.memberplanet.activity.activitypay;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.session.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by joenellis on 04/08/2017.
 */

public class ActivityTigoPay extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextAmount, editTextNumber;
    private Button buttonpay;
    private String code = "tigo-gh";
    private Toolbar mToolbar;
    private String amount, number, fullname, email,apptoken,ClientReference;
    private Spinner spinner;
    private String URL="http://techiesatish.com/demo_api/spinner.php";
    private ArrayList<String> YearGroupName;
    private String mCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_tigo);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Scan");
        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        spinner=findViewById(R.id.account_name);
        editTextNumber = findViewById(R.id.pay_txtNumberPay);
        editTextAmount = findViewById(R.id.pay_txtAmountPay);

        buttonpay = findViewById(R.id.btnPay);

        buttonpay.setOnClickListener(this);

        //Spinner foryear groups
        YearGroupName=new ArrayList<>();
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCountry=spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void userPay() {

        amount = editTextAmount.getText().toString();
        number = editTextNumber.getText().toString();

        if((amount) == null){
            Toast.makeText(this, "Please enter Amount", Toast.LENGTH_LONG).show();
            return;
        }else if ( (number) == null){
            Toast.makeText(this, "Please enter Number", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Requesting...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        apptoken = SharedPrefManager.getInstance(this).getDeviceToken();
        fullname = SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getFullname();
        email = SharedPrefManager.getInstance(getApplicationContext()).getobjectUser().getEmail();
        ClientReference = UUID.randomUUID().toString();

        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.userPay(fullname, email, number, code, amount, apptoken , ClientReference);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (!response.body().getError()) {
                        Toast.makeText(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_LONG).show() ;
                    }else{
                        Toast.makeText(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_LONG).show() ;

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
                            YearGroupName.add(country);
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


    @Override
    public void onClick(View view) {
        if (view == buttonpay) {
            userPay();
        }
    }
}