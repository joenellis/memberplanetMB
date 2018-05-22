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
import java.util.Map;

public class AdapterCategoryMembers extends RecyclerView.Adapter<AdapterCategoryMembers.ProductHolder> {

    private Context mContext;
    private Map<String, ArrayList<ObjectMembers>> objectMembers;

    public AdapterCategoryMembers(Context mContext, Map<String, ArrayList<ObjectMembers>> objectMembers) {
        this.mContext = mContext;
        this.objectMembers = objectMembers;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_members, null);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, final int position) {

//        ObjectMembers obMembers = objectMembers.get(position);
//
//        //String price = "GhC " + objectMembers.getPrice();
//
//        holder.productName.setText(obMembers.getFirstname());
//        holder.productPrice.setText(obMembers.getEmail());
       // Glide.with(this.mContext).load(ObjectMembers.getImage()).into(holder.productImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, ActivityMyProduct.class);
               // intent.putExtra("ID", objectMembers.get(position).getUserid());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return objectMembers.size();
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
