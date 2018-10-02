package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.ChargeType;
import com.example.layout_module.beans.House;
import com.example.layout_module.beans.LinkToServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfBills extends AppCompatActivity {
    //the recyclerview
    RecyclerView recyclerView;
    List<Charge> chargeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_bills);

//getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get tenant id from the session
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        int user_id = sharedPreferences.getInt("user_id", 1);

        //TODO: When there is no bill write "Lucky You are (-:"

        RequestQueue requestQueue = Volley.newRequestQueue(ListOfBills.this);
        //Retrieve building according to the owner
        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "charge/pending/" + user_id;
        Log.d("", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Charge charge = new Charge();

                        charge.setId(jsonObject.getInt("id"));
                        charge.setAmount(jsonObject.getString("amount"));
                        charge.setLastDate(jsonObject.getString("lastDate"));
                        charge.setAddedOn(jsonObject.getString("addedOn"));

                        ChargeType chargeType = new ChargeType();
                        chargeType.setId(jsonObject.getInt("chargeType"));

                        House house = new House();
                        house.setId(jsonObject.getInt("house"));

                        charge.setChargeType(chargeType);
                        charge.setHouse(house);

                        chargeList.add(charge);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                CardAdapterCharge cardAdapterCharge = new CardAdapterCharge(ListOfBills.this, chargeList, new CardAdapterCharge.OnItemClickListener() {
                    @Override
                    public void onItemClick(Charge charge) {
                        Bundle bundle = new Bundle();

                        bundle.putInt("bill_id", charge.getId());
                        bundle.putString("bill_amount", charge.getAmount());
                        bundle.putString("bill_last_date", charge.getLastDate());
                        bundle.putString("bill_added_on", charge.getLastDate());
                        bundle.putInt("bill_type", charge.getChargeType());
                        bundle.putInt("bill_house", charge.getHouse());

                        Intent intent = new Intent(ListOfBills.this, BillDetail.class); //change layout
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                recyclerView.setAdapter(cardAdapterCharge);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
