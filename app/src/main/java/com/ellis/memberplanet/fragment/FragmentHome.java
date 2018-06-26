package com.ellis.memberplanet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ellis.memberplanet.adapter.AdapterNewsletter;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.object.ObjectNewsletter;
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.session.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment {

    final private String KEY = "GET";

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerViewHome;


    private AdapterNewsletter adapter;
    private ArrayList<ObjectNewsletter> newsletters;

    public FragmentHome() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewHome = rootView.findViewById(R.id.recyclerViewHome);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setHasFixedSize(true);

        String yeargroupid = SharedPrefManager.getInstance(getContext()).getobjectUser().getYeargroupid();
        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.newsletter(yeargroupid);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null) {
                    if (!response.body().getError()) {
//                      Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        newsletters = response.body().getObjectNewsletters();
                        adapter = new AdapterNewsletter(getContext(), newsletters);
                        recyclerViewHome.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }


}
