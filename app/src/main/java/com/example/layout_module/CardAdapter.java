package com.example.layout_module;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout_module.beans.Building;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    public interface OnItemClickListener {
        void onItemClick(Building building);
    }

    //we are storing all the buildings in a list
    private List<Building> buildingList;

    private final OnItemClickListener listener;

    //getting the context and building list with constructor
    public CardAdapter(Context mCtx, List<Building> buildingList, OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.buildingList = buildingList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        final LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_data, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(buildingList.get(position), listener);
//        final Building building = buildingList.get(position);
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBuildingName, txtBuildingNoOfFloor, txtBuildingAddress;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtBuildingName = itemView.findViewById(R.id.txtBuildingName);
            txtBuildingNoOfFloor = itemView.findViewById(R.id.txtBuildingNoOfFloor);
            txtBuildingAddress = itemView.findViewById(R.id.txtBuildingAddress);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final Building building, final OnItemClickListener listener) {
            //binding the data with the viewholder views
            txtBuildingName.setText(building.getName());
            txtBuildingNoOfFloor.setText(String.valueOf(building.getNoFloor()));
            txtBuildingAddress.setText(building.getAddress());
            imageView.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.house));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(building);
                }
            });
        }
    }
}