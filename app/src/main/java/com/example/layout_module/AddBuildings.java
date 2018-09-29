package com.example.layout_module;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Owner;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddBuildings extends AppCompatActivity {

    Button update;
    LinearLayout linearLayout;
    EditText name, floor, address;
    String TAG = AddBuildings.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buildings);
        linearLayout = (LinearLayout) findViewById(R.id.container_layout);
        name = findViewById(R.id.name);
        floor = findViewById(R.id.floor);
        address = findViewById(R.id.address);

        update = findViewById(R.id.update);

        //session id//
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        final int user_id = sharedPreferences.getInt("user_id", 1);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.removeAllViews();
                SearchHouseFragment fragmentOne = new SearchHouseFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, fragmentOne).commit();

                Building building = new Building();

                building.setName(name.getText().toString());
                building.setNoFloor(Integer.valueOf(floor.getText().toString()));
                building.setAddress(address.getText().toString());

                Owner owner = new Owner();
                owner.setId(user_id);
                building.setOwner(owner);

                Gson gson = new Gson();
                String gsonTojson = gson.toJson(building);

                JSONObject jsonObject = new JSONObject();
                try {
                    //Conversion to be verified
                    jsonObject = new JSONObject(gsonTojson);
                    Log.d(TAG, jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/building/save";

                RequestQueue requestQueue = Volley.newRequestQueue(AddBuildings.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(AddBuildings.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
