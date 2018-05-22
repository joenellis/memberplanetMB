package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.os.Bundle;
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
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.object.ObjectYearGroup;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterCategoryRecyclerView extends RecyclerView.Adapter<AdapterCategoryRecyclerView.CategoryHolder> {

    private ArrayList<Map<String, ArrayList<ObjectMembers>>> categories;
    private Context mContext;
    private ArrayList mImage;
   // private String[] category = {"Tubers","Fruits","Vegetables", "Grains", "Dairy/Fish"};


    public AdapterCategoryRecyclerView(Context mContext, ArrayList<Map<String, ArrayList<ObjectMembers>>> categories) {
        this.mContext = mContext;
        this.categories = categories;
    }


    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_category, null);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {


       // holder.mCategory.setText(categories.get());
        String browse = "Connect with recently joined alumni in ";//+ category[position].toLowerCase()+ " group ";
        holder.mSubtitle.setText(browse);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(new AdapterCategoryMembers(mContext, categories.get(position)));

        final Bundle bundle = new Bundle();
        bundle.putString("Category", String.valueOf(position + 1));

        final Fragment fragment = new FragmentMore();
        fragment.setArguments(bundle);

        holder.mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity)mContext).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homeLayout, fragment)
                    .addToBackStack("Year Groups")
                    .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
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
