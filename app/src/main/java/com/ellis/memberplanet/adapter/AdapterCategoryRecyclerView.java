package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ellis.memberplanet.R;
import com.ellis.memberplanet.fragment.FragmentMore;
import com.ellis.memberplanet.object.ObjectMembers;

import java.util.ArrayList;
import java.util.Map;

public class AdapterCategoryRecyclerView extends RecyclerView.Adapter<AdapterCategoryRecyclerView.CategoryHolder> {

    //    private ArrayList<Map<String, ArrayList<ObjectMembers>>> categories;
    //private Context mContext;
    private ArrayList mImage;
    private LayoutInflater inflater;
    private Map<String, ArrayList<ObjectMembers>> yearGroupMap;
    // private String[] category = {"Tubers","Fruits","Vegetables", "Grains", "Dairy/Fish"};
    private String[] yearGroups;

    public AdapterCategoryRecyclerView(Map<String, ArrayList<ObjectMembers>> yearGroupMap) {
        //this.mContext = mContext;
        this.yearGroupMap = yearGroupMap;
        if (yearGroupMap != null) {
            yearGroups = new String[yearGroupMap.size()];
            yearGroups = yearGroupMap.keySet().toArray(yearGroups);
        }
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.holder_category, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        final Context context = inflater.getContext();
        String yearGroup = yearGroups[position];
        // holder.mCategory.setText(categories.get());
        String browse = "Connect with recently joined alumni in " + yearGroup.toLowerCase()+ " group ";
        holder.mSubtitle.setText(browse);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(new AdapterCategoryMembers(yearGroupMap.get(yearGroup)));

        final Bundle bundle = new Bundle();
        bundle.putString("Category", String.valueOf(position + 1));

        final Fragment fragment = new FragmentMore();
        fragment.setArguments(bundle);

        holder.mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeLayout, fragment)
                        .addToBackStack("Year Groups")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return yearGroupMap == null ? 0 : yearGroupMap.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        Button mMore;
        TextView mCategory;
        TextView mSubtitle;
        RecyclerView mRecyclerView;


        CategoryHolder(View itemView) {
            super(itemView);

            mMore = itemView.findViewById(R.id.holderCategory_btnMore);
            mCategory = itemView.findViewById(R.id.holderCategory_txtCategory);
            mSubtitle = itemView.findViewById(R.id.holderCategory_txtSubtitle);
            mRecyclerView = itemView.findViewById(R.id.holderCategory_recyclerView);
        }
    }
}
