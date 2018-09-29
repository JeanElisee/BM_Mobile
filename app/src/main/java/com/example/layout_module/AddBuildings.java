package com.example.layout_module;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.layout_module.beans.Session;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;

public class AddBuildings extends AppCompatActivity {
    private Session session;//global variable

    Button addBuilding;
    //    LinearLayout linearLayout;
    EditText txtBuildingName, txtBuildingNofloor, txtBuildingAddress;
    String TAG = AddBuildings.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buildings);
//        linearLayout = (LinearLayout) findViewById(R.id.container_layout);

        txtBuildingName = findViewById(R.id.txt_building_name);
        txtBuildingNofloor = findViewById(R.id.txt_building_no_floor);
        txtBuildingAddress = findViewById(R.id.txt_building_address);

        addBuilding = findViewById(R.id.add_building_to_db);

        addBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
                int user_id = sharedPreferences.getInt("user_id", 1);

                Toast.makeText(getApplicationContext(), "" + user_id, Toast.LENGTH_SHORT).show();

                Building building = new Building();

                building.setName(txtBuildingName.getText().toString());
                building.setNoFloor(Integer.valueOf(txtBuildingNofloor.getText().toString()));
                building.setAddress(txtBuildingAddress.getText().toString());

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
