package com.example.layout_module;

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
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Owner;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilding extends AppCompatActivity {


    //the recyclerview
    RecyclerView recyclerView;
    final ArrayList<String> name = new ArrayList<String>();
    final ArrayList<String> floor = new ArrayList<String>();
    final ArrayList<String> address = new ArrayList<String>();

    String Tag=SelectBuilding.class.getSimpleName();
   // final SharedPreferences sharedPreferences1=getSharedPreferences(Login.PREF_NAME,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_building_activity);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //charge bundle
        final SharedPreferences  mPrefs = getSharedPreferences(AddBills.PREF_NAME,MODE_PRIVATE);
        final ArrayList<Charge> chargeList = new ArrayList<>();
        Gson gson = new Gson();
        String json = mPrefs.getString("chargeObject","");
        Charge charge = gson.fromJson(json, Charge.class);
        chargeList.add(charge);
        Log.d(Tag,charge.toString());
        Toast.makeText(this,""+json,Toast.LENGTH_LONG).show();


        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/building/find_by_owner_id/1";
        RequestQueue requestQueue = Volley.newRequestQueue(SelectBuilding.this);

        final List<Building> buildingList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Building building = new Building();

                        building.setId(jsonObject.getInt("id"));
                        building.setName(jsonObject.getString("name"));
                        building.setNoFloor(jsonObject.getInt("noFloor"));
                        building.setAddedOn(jsonObject.getString("addedOn"));
                        building.setAddress(jsonObject.getString("address"));

                        Owner owner = new Owner();
                        owner.setId(jsonObject.getInt("owner"));

                        building.setOwner(owner);

                        Log.d("", building.toString());


                        buildingList.add(building);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.d("tag", buildingList.toString());

                CardAdapter adapter = new CardAdapter(SelectBuilding.this, buildingList,chargeList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonArrayRequest);





    }
}
