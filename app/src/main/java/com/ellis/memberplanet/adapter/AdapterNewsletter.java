package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ellis.memberplanet.R;
import com.ellis.memberplanet.object.ObjectNewsletter;

import java.util.List;

//import com.squareup.picasso.Picasso;

public class AdapterNewsletter extends RecyclerView.Adapter<AdapterNewsletter.ProductHolder> {

    private Context mContext;
    private List<ObjectNewsletter> newsletters;

    public AdapterNewsletter(Context mContext, List<ObjectNewsletter> newsletters) {
        this.mContext = mContext;
        this.newsletters = newsletters;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_newsletter, null);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

        final ObjectNewsletter newsletter = newsletters.get(position);

        holder.name.setText(newsletter.getFullname());
        holder.title.setText(newsletter.getTitle());
        holder.description.setText(newsletter.getDescription());
        holder.date.setText(newsletter.getPublishdate());
        Glide.with(this.mContext).load(newsletter.getPhoto()).into(holder.adminImage);
        Glide.with(this.mContext).load(newsletter.getImage()).into(holder.newsfeedImage);

    }

    @Override
    public int getItemCount() {
        return newsletters.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder {

        TextView name, date, description,title;
        ImageView adminImage, newsfeedImage;


        ProductHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.holderNewsFeeds_name);
            date = itemView.findViewById(R.id.holderNewsFeeds_date);
            title = itemView.findViewById(R.id.holderNewsFeeds_title);
            description = itemView.findViewById(R.id.holderNewsFeeds_description);
            adminImage = itemView.findViewById(R.id.holderNewsFeeds_adminimg);
            newsfeedImage = itemView.findViewById(R.id.holderNewsFeeds_newsfeedimg);

        }
    }
}
