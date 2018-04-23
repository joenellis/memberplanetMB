package com.ellis.memberplanet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ellis.memberplanet.adapter.AdapterMyMembers;
import com.ellis.memberplanet.api.Api;
import com.ellis.memberplanet.api.ApiCall;
import com.ellis.memberplanet.api.Result;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.object.ObjectUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMore extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView mRecyclerViewMore;
    private ArrayList<ObjectUser> products;
    private AdapterMyMembers adapter;

    public FragmentMore() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);

        String args = getArguments().getString("Category");


        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewMore = rootView.findViewById(R.id.recyclerViewMore);
        mRecyclerViewMore.setLayoutManager(layoutManager);
        mRecyclerViewMore.setHasFixedSize(true);


        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.productcaegory(args);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null) {
                    if (!response.body().getError()) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        products = response.body().getObjectUsers();
                        adapter = new AdapterMyMembers(getContext(), products);
                        mRecyclerViewMore.setAdapter(adapter);

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

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.home_menu, menu);
//
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                final List<ObjectProduct> filteredModelList = adapter.filter(products, newText);
//                adapter.animateTo(filteredModelList);
//                mRecyclerViewMore.scrollToPosition(0);
//
//                return true;
//            }
//        });
//
//    }
//

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //displaySelectedScreen(item.getItemId());
        return true;

    }
}
