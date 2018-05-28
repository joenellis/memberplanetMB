package com.ellis.memberplanet.fragment;

import android.os.Bundle;
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
import com.ellis.memberplanet.object.ObjectMembers;
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.object.ObjectUser;
import com.ellis.memberplanet.session.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMyMembers extends Fragment {


    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    private ArrayList<ObjectMembers> members;
    private AdapterMyMembers adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_products, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewMyProducts);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        String yeargroupid = SharedPrefManager.getInstance(getActivity()).getobjectUser().getYeargroupid();


        Api api = new Api();
        ApiCall service = api.getRetro().create(ApiCall.class);
        Call<Result> call = service.mymembers(yeargroupid);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null) {
                if (!response.body().getError()) {
                    Toast.makeText(getActivity(),response.body().getMessage(), Toast.LENGTH_LONG).show() ;
                    members = response.body().getObjectMembers();
                    adapter = new AdapterMyMembers(getContext(), members);
                    recyclerView.setAdapter(adapter);

                } else {
           //         Toast.makeText(getActivity(),response.body().getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.home_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final List<ObjectMembers> filteredModelList = adapter.filter(members, newText);
                adapter.animateTo(filteredModelList);
                recyclerView.scrollToPosition(0);

                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //displaySelectedScreen(item.getItemId());
        return true;

    }

}
