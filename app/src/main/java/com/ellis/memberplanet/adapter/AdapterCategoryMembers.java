package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellis.memberplanet.activity.ActivityMyProduct;
import com.ellis.memberplanet.object.ObjectMembers;
import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.R;
import com.bumptech.glide.Glide;
import com.ellis.memberplanet.object.ObjectYearGroup;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategoryMembers extends RecyclerView.Adapter<AdapterCategoryMembers.ProductHolder> {

    private Context mContext;
    private List<ObjectMembers> yearGroups;

    public AdapterCategoryMembers(Context mContext, List<ObjectMembers> products) {
        this.mContext = mContext;
        this.yearGroups = products;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_members, null);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

       // final ObjectYearGroup yeargroups = yearGroups.get(position);

//        String price = "GhC " + yeargroups.getPrice();
//
//        holder.productName.setText(yeargroups.getProductname());
//        holder.productPrice.setText(price);
//        Glide.with(this.mContext).load(yeargroups.getImage()).into(holder.productImage);
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(mContext, ActivityMyProduct.class);
//                intent.putExtra("ID", yeargroups.getProduct_id());
//                mContext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return yearGroups.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productPrice;
        ImageView productImage;


        ProductHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.txtItem);
            productPrice = itemView.findViewById(R.id.txtPrice);
            productImage = itemView.findViewById(R.id.imgItem);
        }
    }
}
