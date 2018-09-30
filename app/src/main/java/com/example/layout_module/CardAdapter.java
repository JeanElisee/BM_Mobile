package com.example.layout_module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout_module.beans.Building;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ProductViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Building> buildingList;

    //getting the context and product list with constructor
    public CardAdapter(Context mCtx, List<Building> buildingList) {
        this.mCtx = mCtx;
        this.buildingList = buildingList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        final LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_data, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        //getting the product of the specified position
        final Bundle bundle = new Bundle();
        final Building building = buildingList.get(position);

        //binding the data with the viewholder views
        holder.txtBuildingName.setText(building.getName());
        holder.txtBuildingNoOfFloor.setText(String.valueOf(building.getNoFloor()));
        holder.txtBuildingAddress.setText(building.getAddress());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.house));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("", holder.txtBuildingName.getText().toString());
//                bundle.putString("floornum", holder.txtBuildingNoOfFloor.getText().toString());

//                Intent intent = new Intent(mCtx, SelectHouse.class);
//                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtBuildingName, txtBuildingNoOfFloor, txtBuildingAddress;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            txtBuildingName = itemView.findViewById(R.id.txtBuildingName);
            txtBuildingNoOfFloor = itemView.findViewById(R.id.txtBuildingNoOfFloor);
            txtBuildingAddress = itemView.findViewById(R.id.txtBuildingAddress);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}