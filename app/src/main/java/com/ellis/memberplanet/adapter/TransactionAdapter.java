package com.ellis.memberplanet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ellis.memberplanet.R;
import com.ellis.memberplanet.object.Trans;

import java.util.List;

/**
 * Created by joenellis on 21/08/2017.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Trans> Trans;
    private Context mCtx;

    public TransactionAdapter(List<Trans> list, Context mCtx) {
        this.Trans = list;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_transactions, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(TransactionAdapter.ViewHolder holder, int position) {
        Trans trans = Trans.get(position);
        holder.description.setText(trans.getDescription());
        holder.amount.setText("GHS: " + trans.getAmount());
        holder.date.setText(trans.getDate_created());

    }

    @Override
    public int getItemCount() {
        return Trans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description, amount, date;

        public ViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.description);
            amount = view.findViewById(R.id.amount);
            date = view.findViewById(R.id.dated);
        }
    }
}
