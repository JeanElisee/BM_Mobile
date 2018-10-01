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
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Owner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilding extends AppCompatActivity {
    //the recyclerview
    RecyclerView recyclerView;

    List<Building> buildingList = new ArrayList<>();

    public static final String PREF_NAME = "building_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_building_activity);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences_action = getSharedPreferences(Actions.PREF_ACTION, 0);
        final String action = sharedPreferences_action.getString("action", "DEFAULT");
//        Toast.makeText(SelectBuilding.this, "" + action, Toast.LENGTH_LONG).show();


        RequestQueue requestQueue = Volley.newRequestQueue(SelectBuilding.this);

        // Get owner id from the session
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        int user_id = sharedPreferences.getInt("user_id", 1);

        //Retrieve building according to the owner
        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/building/find_by_owner_id/" + user_id;
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

                        buildingList.add(building);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                CardAdapter adapter = new CardAdapter(SelectBuilding.this, buildingList, new CardAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Building building) {
                        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //Store information about the building clicked in the shared preference
                        editor.putInt("building_id", building.getId());
                        editor.putString("building_name", building.getName());
                        editor.putInt("building_no_floor", building.getNoFloor());
                        editor.putString("building_address", building.getAddress());
                        editor.apply();

                        if (action.equals("house")) {
                            Intent intent = new Intent(SelectBuilding.this, SummaryActivity.class);
                            startActivity(intent);
                        } else {
                            //                        Just to pass the no of floor
                            Bundle bundle = new Bundle();
                            bundle.putInt("no_of_floor", building.getNoFloor());
                            bundle.putInt("building_id", building.getId());

                            Intent intent = new Intent(SelectBuilding.this, SelectHouse.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                });
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
