package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.activity.ActivityMyProduct;
import com.ellis.memberplanet.object.ObjectMembers;

import java.util.ArrayList;

//import com.squareup.picasso.Picasso;

public class AdapterCategoryMembers extends RecyclerView.Adapter<AdapterCategoryMembers.ProductHolder> {

    //private Context mContext;
    private ArrayList<ObjectMembers> objectMembers;
    private LayoutInflater inflater;

    public AdapterCategoryMembers(ArrayList<ObjectMembers> objectMembers) {
        this.objectMembers = objectMembers;
       //this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.holder_members, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, final int position) {
        final Context context = holder.itemView.getContext();

        // TODO You get each member here
        final ObjectMembers member = this.objectMembers.get(position);

        //String price = "GhC " + objectMembers.getPrice();

        holder.productName.setText(member.getFirstname());
        holder.productPrice.setText(member.getEmail());
       // Glide.with(mContext).load(member.getImage()).into(holder.productImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ActivityMyProduct.class);
                 intent.putExtra("ID", member.getUserid());
                context.startActivity(intent);
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
