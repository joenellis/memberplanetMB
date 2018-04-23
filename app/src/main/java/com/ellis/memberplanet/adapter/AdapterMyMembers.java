package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellis.memberplanet.R;
import com.ellis.memberplanet.activity.ActivityMyProduct;
import com.ellis.memberplanet.object.ObjectUser;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class AdapterMyMembers extends RecyclerView.Adapter<AdapterMyMembers.ProductHolder> {

    private Context mContext;
    private List<ObjectUser> mMembers;

    public AdapterMyMembers(Context mContext, List<ObjectUser> mMembers) {
        this.mContext = mContext;
        this.mMembers = mMembers;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_my_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

        final ObjectUser members = mMembers.get(position);

        holder.text1.setText(members.getFirstname() + " " + members.getLastname());
        holder.text2.setText(members.getProfession());
        Glide.with(this.mContext).load(members.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityMyProduct.class);
                intent.putExtra("ID", members.getUser_id());
                mContext.startActivity(intent);
            }
        });

    }

    public List<ObjectUser> filter(List<ObjectUser> products, String query) {
        query = query.toLowerCase();

        ArrayList<ObjectUser> filteredCompanyList = new ArrayList<>();

        for (ObjectUser item : products) {
            final String productName = item.getFullname().toLowerCase();
            final String productPrice = item.getEmail().toLowerCase();
            if (productName.contains(query) || productPrice.contains(query)) {
                filteredCompanyList.add(item);
            }
        }
        return filteredCompanyList;
    }

    public void animateTo(List<ObjectUser> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ObjectUser> newModels) {
        for (int i = mMembers.size() - 1; i >= 0; i--) {
            final ObjectUser model = mMembers.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ObjectUser> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ObjectUser model = newModels.get(i);
            if (!mMembers.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ObjectUser> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ObjectUser model = newModels.get(toPosition);
            final int fromPosition = mMembers.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ObjectUser removeItem(int position) {
        final ObjectUser model = mMembers.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ObjectUser model) {
        mMembers.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ObjectUser model = mMembers.remove(fromPosition);
        mMembers.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;

        ImageView imageView;

         ProductHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.holderMyProduct_txtProductName);
            text2 = itemView.findViewById(R.id.holderMyProduct_txtProductPrice);

            imageView = itemView.findViewById(R.id.holderMyProduct_imgProductImage);

        }
    }
}
