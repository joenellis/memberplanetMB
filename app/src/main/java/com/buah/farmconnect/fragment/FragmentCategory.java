package com.buah.farmconnect.fragment;

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

import com.buah.farmconnect.R;
import com.buah.farmconnect.adapter.AdapterCategoryRecyclerView;
import com.buah.farmconnect.api.Api;
import com.buah.farmconnect.api.ApiCall;
import com.buah.farmconnect.api.Result;
import com.buah.farmconnect.object.ObjectProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategory extends Fragment {

    ArrayList<ArrayList<ObjectProduct>> categories = new ArrayList<>();

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

                        if (response.body().getObjectProductTubers() != null) {
                            if (response.body().getObjectProductTubers().size() != 0)
                                categories.add(response.body().getObjectProductTubers());
                        }
                        if (response.body().getObjectProductFruits() != null) {
                            if (response.body().getObjectProductFruits().size() != 0)
                                categories.add(response.body().getObjectProductFruits());
                        }
                        if (response.body().getObjectProductVegetables() != null) {
                            if (response.body().getObjectProductVegetables().size() != 0)
                                categories.add(response.body().getObjectProductVegetables());
                        }
                        if (response.body().getObjectProductGrains() != null) {
                            if (response.body().getObjectProductGrains().size() != 0)
                                categories.add(response.body().getObjectProductGrains());
                        }
                        if (response.body().getObjectProductDairyFish() != null) {
                            if (response.body().getObjectProductDairyFish().size() != 0)
                                categories.add(response.body().getObjectProductDairyFish());
                        }


                        AdapterCategoryRecyclerView adapter;
                        adapter = new AdapterCategoryRecyclerView(getContext(), categories);
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
