package com.ellis.memberplanet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ellis.memberplanet.R;
import com.ellis.memberplanet.adapter.AdapterCategoryRecyclerView;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.object.ObjectMembers;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategory extends Fragment {

    ArrayList<Map<String, ArrayList<ObjectMembers>>> categories = new ArrayList<Map<String, ArrayList<ObjectMembers>>>();

    RecyclerView.LayoutManager mLayoutManagerNew;
    RecyclerView mRecyclerView;
    View rootView;

    public FragmentCategory() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_category, container, false);

        initialize();

        mRecyclerView.setLayoutManager(mLayoutManagerNew);
        //mRecyclerView.setAdapter(new AdapterCategoryRecyclerView(getContext(), products.getObjectProducts()));


        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.category();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null) {
                    if (!response.body().getError()) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

//                        if (response.body().getYearGroupMap() != null) {
//
//                          for (int i = 0; i < response.body().getYearGroupMap().size()  ; i++) {
//                              categories.add(response.body().getYearGroupMap());
//                          }
//
//                        }

                        AdapterCategoryRecyclerView adapter;
                        adapter = new AdapterCategoryRecyclerView(response.body().getYearGroupMap());
                        mRecyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    private void initialize() {
        mRecyclerView = rootView.findViewById(R.id.category_recyclerView);
        mLayoutManagerNew = new LinearLayoutManager(getActivity());
    }
}
