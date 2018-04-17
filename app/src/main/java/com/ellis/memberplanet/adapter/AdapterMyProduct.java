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
import com.ellis.memberplanet.object.ObjectProduct;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class AdapterMyProduct extends RecyclerView.Adapter<AdapterMyProduct.ProductHolder> {

    private Context mContext;
    private List<ObjectProduct> mProducts;

    public AdapterMyProduct(Context mContext, List<ObjectProduct> mProducts) {
        this.mContext = mContext;
        this.mProducts = mProducts;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_my_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

        final ObjectProduct product = mProducts.get(position);

        holder.text1.setText(product.getProductname());
        holder.text2.setText(product.getPrice());
        Glide.with(this.mContext).load(product.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityMyProduct.class);
                intent.putExtra("ID", product.getProduct_id());
                mContext.startActivity(intent);
            }
        });

    }

    public List<ObjectProduct> filter(List<ObjectProduct> products, String query) {
        query = query.toLowerCase();

        ArrayList<ObjectProduct> filteredCompanyList = new ArrayList<>();

        for (ObjectProduct item : products) {
            final String productName = item.getProductname().toLowerCase();
            final String productPrice = item.getPrice().toLowerCase();
            if (productName.contains(query) || productPrice.contains(query)) {
                filteredCompanyList.add(item);
            }
        }
        return filteredCompanyList;
    }

    public void animateTo(List<ObjectProduct> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ObjectProduct> newModels) {
        for (int i = mProducts.size() - 1; i >= 0; i--) {
            final ObjectProduct model = mProducts.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ObjectProduct> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ObjectProduct model = newModels.get(i);
            if (!mProducts.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ObjectProduct> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ObjectProduct model = newModels.get(toPosition);
            final int fromPosition = mProducts.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ObjectProduct removeItem(int position) {
        final ObjectProduct model = mProducts.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ObjectProduct model) {
        mProducts.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ObjectProduct model = mProducts.remove(fromPosition);
        mProducts.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
    @Override
    public int getItemCount() {
        return mProducts.size();
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
