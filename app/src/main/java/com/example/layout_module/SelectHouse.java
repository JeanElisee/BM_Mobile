package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.House;
import com.example.layout_module.beans.HouseType;
import com.example.layout_module.beans.LinkToServer;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectHouse extends AppCompatActivity {
    //the recyclerview
    RecyclerView recyclerView;

    public static final String PREF_NAME = "house_info";

    Spinner spinner;
    ArrayList<String> floorList = new ArrayList<>();
    Bundle data;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_house);
        spinner = findViewById(R.id.floor_num_spinner);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<House> houseList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int noOfFloor = bundle.getInt("no_of_floor");
        final int buildingId = bundle.getInt("building_id");


        for (int i = 0; i < noOfFloor; i++) {
            floorList.add("00" + i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SelectHouse.this, android.R.layout.simple_spinner_dropdown_item, floorList);
        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                houseList.clear();
                RequestQueue requestQueue = Volley.newRequestQueue(SelectHouse.this);
                // Retrieve charge type and store in the dropdown list
                String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/house/find_per_floor/" + buildingId + "/" + spinner.getSelectedItemPosition();
                Log.d("", url);
                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                House house = new House();

                                house.setId(jsonObject.getInt("id"));
                                house.setDoorNo(jsonObject.getString("doorNo"));
                                house.setFloorNo(jsonObject.getString("floorNo"));
                                house.setRentAmount(jsonObject.getString("rentAmount"));
                                house.setDeposit(jsonObject.getString("deposit"));

                                HouseType houseType = new HouseType();
                                houseType.setId(jsonObject.getInt("houseType"));

                                house.setHouseType(houseType);
                                houseList.add(house);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        CardAdapterHouse adapterHouse = new CardAdapterHouse(SelectHouse.this, houseList, new CardAdapterHouse.OnItemClickListener() {
                            @Override
                            public void onItemClick(House house) {
                                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                                //Store information about the building clicked in the shared preference
                                editor.putInt("house_id", house.getId());
                                editor.putString("house_door_no", house.getDoorNo());
                                editor.putString("house_floor_no", house.getFloorNo());
                                editor.putString("house_rent_amount", house.getRentAmount());
                                editor.putString("house_deposit", house.getDeposit());
                                editor.apply();

//                                Toast.makeText(getApplicationContext(), house.toString(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SelectHouse.this, SummaryActivity.class);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapterHouse);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(jsonArrayRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
