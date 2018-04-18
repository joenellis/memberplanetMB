package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellis.memberplanet.object.ObjectProduct;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.activity.ActivityViewProduct;
import com.bumptech.glide.Glide;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ProductHolder> {

    private Context mContext;
    private List<ObjectProduct> products;

    public AdapterEvent(Context mContext, List<ObjectProduct> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_events, null);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

        final ObjectProduct product = products.get(position);

        String price = "GhC " + product.getPrice();

        holder.productName.setText(product.getProductname());
        holder.productPrice.setText(price);
        Glide.with(this.mContext).load(product.getImage()).into(holder.productImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(mContext, ActivityViewProduct.class);
//                intent.putExtra("ID", product.getProduct_id());
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productPrice;
        ImageView productImage;


        ProductHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.holderMyProduct_txtTitle);
            productPrice = itemView.findViewById(R.id.holderMyProduct_txtDescri);
            productImage = itemView.findViewById(R.id.holderNewsFeeds_img3);
        }
    }
}
