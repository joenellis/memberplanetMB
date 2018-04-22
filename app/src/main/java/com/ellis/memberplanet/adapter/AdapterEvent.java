package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellis.memberplanet.object.ObjectEvent;
import com.ellis.memberplanet.R;
import com.bumptech.glide.Glide;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ProductHolder> {

    private Context mContext;
    private List<ObjectEvent> events;

    public AdapterEvent(Context mContext, List<ObjectEvent> events) {
        this.mContext = mContext;
        this.events = events;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.holder_events, null);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

        final ObjectEvent event = events.get(position);

        holder.eventname.setText(event.getName());
        holder.date.setText("Date: " + event.getDate());
        holder.startdate.setText("Start time: " + event.getStartdate());
        holder.enddate.setText("End time: " + event.getEndate());
        holder.description.setText(event.getDescription());
        holder.type.setText(event.getType());

        Glide.with(this.mContext).load(event.getImage()).into(holder.image);

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
        return events.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder {

        TextView eventname,date,description,startdate,enddate,type;
        ImageView image;


        ProductHolder(View itemView) {
            super(itemView);

            eventname = itemView.findViewById(R.id.holderevent_name);
            date = itemView.findViewById(R.id.holderevent_date);
            description = itemView.findViewById(R.id.holderevent_description);
            startdate = itemView.findViewById(R.id.holderevent_startdate);
            enddate = itemView.findViewById(R.id.holderevent_enddate);
            type = itemView.findViewById(R.id.holderevent_type);
            image = itemView.findViewById(R.id.holderevent_img);
        }
    }
}
