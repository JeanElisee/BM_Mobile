package com.example.layout_module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.House;

import java.util.List;

public class CardAdapterCharge extends RecyclerView.Adapter<CardAdapterCharge.ViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    public interface OnItemClickListener {
        void onItemClick(Charge charge);
    }

    //we are storing all the buildings in a list
    private List<Charge> chargeList;

    private final OnItemClickListener listener;

    //getting the context and building list with constructor
    public CardAdapterCharge(Context mCtx, List<Charge> chargeList, OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.chargeList = chargeList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        final LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_data_bill_tenant, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(chargeList.get(position), listener);
//        final Building building = buildingList.get(position);
    }

    @Override
    public int getItemCount() {
        return chargeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBillAmount, txtBillLastDate, txtBillAddedOn;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtBillAmount = itemView.findViewById(R.id.txtBillsAmount);
            txtBillLastDate = itemView.findViewById(R.id.txtBillLastDate);
            txtBillAddedOn = itemView.findViewById(R.id.txtAddedOn);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final Charge charge, final OnItemClickListener listener) {
            //binding the data with the viewholder views
            txtBillAmount.setText(mCtx.getString(R.string.bill_amount) + charge.getAmount());
            txtBillLastDate.setText(mCtx.getString(R.string.last_date) + charge.getLastDate());
            txtBillAddedOn.setText(mCtx.getString(R.string.bill_added_on) + charge.getAddedOn());
            imageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.house));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(charge);
                }
            });
        }
    }
}