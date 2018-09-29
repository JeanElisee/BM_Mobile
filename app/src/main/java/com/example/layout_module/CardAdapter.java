
package com.example.layout_module;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout_module.Beans.Building;
import com.example.layout_module.Beans.Charge;
import com.google.gson.Gson;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Building> buildingList;
    private List<Charge> chargeList;

    //getting the context and product list with constructor
    public CardAdapter(Context mCtx, List<Building> buildingList,List<Charge> chargeList) {
        this.mCtx = mCtx;
        this.buildingList=buildingList;
        this.chargeList=chargeList;
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
        final Bundle bundle=new Bundle();
        final Building building=buildingList.get(position);
        final Charge charge=chargeList.get(0);
        //binding the data with the viewholder views
        holder.textViewTitle.setText(building.getName());
        holder.textViewFloor.setText("" + building.getNoFloor());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.house));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("floornum",holder.textViewFloor.getText().toString());
                Intent intent=new Intent(mCtx,SelectHouse.class);
                //intent.putExtra("buildingObject",building);
                //intent.putExtra("chargeObject",  charge);
                final SharedPreferences  mPrefs = mCtx.getSharedPreferences(AddBills.PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String Chargejson = gson.toJson(charge);
                String Buildingjson= gson.toJson(building);
                prefsEditor.putString("chargeObject", Chargejson);
                prefsEditor.putString("buildingObject", Buildingjson);
                prefsEditor.apply();
                intent.putExtras(bundle);
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return buildingList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle,textViewFloor;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewFloor=itemView.findViewById(R.id.textViewFloor);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}