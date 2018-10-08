package com.example.layout_module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.House;

import java.util.List;

public class CardAdapterHouse extends RecyclerView.Adapter<CardAdapterHouse.ViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    public interface OnItemClickListener {
        void onItemClick(House house);
    }

    //we are storing all the buildings in a list
    private List<House> houseList;

    private final OnItemClickListener listener;

    //getting the context and building list with constructor
    public CardAdapterHouse(Context mCtx, List<House> houseList, OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.houseList = houseList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        final LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_data_house, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(houseList.get(position), listener);
//        final Building building = buildingList.get(position);
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHouseDoorNo, txtHouseRentAmount, txtHouseDeposit;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHouseDoorNo = itemView.findViewById(R.id.txtHouseDoorNo);
            txtHouseRentAmount = itemView.findViewById(R.id.txtHouseRentAmount);
            txtHouseDeposit = itemView.findViewById(R.id.txtHouseDeposit);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final House house, final OnItemClickListener listener) {
            //binding the data with the viewholder views
            txtHouseDoorNo.setText(house.getDoorNo());
            txtHouseRentAmount.setText(house.getRentAmount());
            txtHouseDeposit.setText(house.getDeposit());
            imageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.house));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(house);
                }
            });
        }
    }
}