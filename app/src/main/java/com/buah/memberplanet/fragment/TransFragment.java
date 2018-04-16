package com.buah.memberplanet.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.buah.memberplanet.adapter.AdapterMyProduct;
import com.buah.memberplanet.api.Api;
import com.buah.memberplanet.api.ApiCall;
import com.buah.memberplanet.api.Result;
import com.buah.memberplanet.R;
import com.buah.memberplanet.session.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joenellis on 11/10/2017.
 */

public class TransFragment extends Fragment {

    private RecyclerView RecyclerViewtrans;
    private RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        RecyclerViewtrans = rootView.findViewById(R.id.recyclerViewtrans);
        layoutManager = new LinearLayoutManager(getActivity());
        RecyclerViewtrans .setLayoutManager(layoutManager);

        //RecyclerViewtrans.setHasFixedSize(true);
        RecyclerViewtrans.setLayoutManager(new LinearLayoutManager(getActivity()));



//        /////////API calls
//        ApiInterface api = new ApiInterface();
//        APIs service = api.getRetro().create(APIs.class);
//        String id = SharedPrefManager.getInstance(getActivity()).getuser().getId();
//
//        //defining the call
//        Call<Result> call;
//        call = service.userTransaction(id);
//
//        //calling the api
//        call.enqueue(new Callback<Result>() {
//
//
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//
//                if (!response.body().getError()) {
//                    adapter = new TransactionAdapter(response.body().getTrans(), getActivity());
//                    RecyclerViewtrans.setAdapter(adapter);
//                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//
//                } else {
//                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

    return rootView;
    }
}
