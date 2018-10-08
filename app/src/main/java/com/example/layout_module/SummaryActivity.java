package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.ChargeType;
import com.example.layout_module.beans.House;
import com.example.layout_module.beans.HouseType;
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Occupy;
import com.example.layout_module.beans.Tenant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SummaryActivity extends AppCompatActivity {

    TextView txtSummary;
    Button button,backtoDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        txtSummary = findViewById(R.id.txtSummary);
        button = findViewById(R.id.button);
        backtoDash = findViewById(R.id.gotodash);

        SharedPreferences sharedPreferences_action = getSharedPreferences(Actions.PREF_ACTION, 0);
        final String action = sharedPreferences_action.getString("action", "DEFAULT");

        txtSummary.setText("Do you want to add that " + action +" ?");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (action) {
                    case "bill":
                        saveBill();
                        break;
                    case "house":
                        saveHouse();
                        break;
                    case "tenant":
                        saveTenant();
                        break;
                }
            }
        });

backtoDash.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(SummaryActivity.this,OwnerDashboard.class);
        startActivity(intent);
    }
});
    }

    private void saveBill() {
        SharedPreferences sharedPreferences_bill = getSharedPreferences(AddBills.PREF_NAME, 0);

        Charge charge = new Charge();
        charge.setAmount(sharedPreferences_bill.getString("charge_amount", "DEFAULT"));
        charge.setLastDate(sharedPreferences_bill.getString("charge_last_date", "DEFAULT"));

        ChargeType chargeType = new ChargeType();
        chargeType.setId(sharedPreferences_bill.getInt("charge_type_id", 1));

        charge.setChargeType(chargeType);

        SharedPreferences sharedPreferences_house = getSharedPreferences(SelectHouse.PREF_NAME, 0);
        House house = new House();
        house.setId(sharedPreferences_house.getInt("house_id", 1));

        charge.setHouse(house);

        Gson gson = new GsonBuilder().create();
        String jsonFromObject = gson.toJson(charge);
//        Log.d("JSON", jsonFromObject);

        JSONObject jsonObject = new JSONObject();
        try {
            //Conversion to be verified
            jsonObject = new JSONObject(jsonFromObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "charge/save";

        RequestQueue requestQueue = Volley.newRequestQueue(SummaryActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(SummaryActivity.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
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

    private void saveHouse() {
        SharedPreferences sharedPreferences_house = getSharedPreferences(AddHouse.PREF_NAME, 0);

        House house = new House();
        house.setDoorNo(sharedPreferences_house.getString("h_door_no", "DEFAULT"));
        house.setRentAmount(sharedPreferences_house.getString("h_rent_amount", "DEFAULT"));
        house.setDeposit(sharedPreferences_house.getString("h_deposit", "DEFAULT"));

        HouseType houseType = new HouseType();
        houseType.setId(sharedPreferences_house.getInt("houseType", 1));

        house.setHouseType(houseType);

        SharedPreferences sharedPreferences_building = getSharedPreferences(SelectBuilding.PREF_NAME, 0);
        Building building = new Building();
        building.setId(sharedPreferences_building.getInt("building_id", 1));

        house.setBuilding(building);

        Gson gson = new GsonBuilder().create();
        String jsonFromObject = gson.toJson(house);
//        Log.d("JSON", jsonFromObject);

        JSONObject jsonObject = new JSONObject();
        try {
            //Conversion to be verified
            jsonObject = new JSONObject(jsonFromObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "house/save";

        RequestQueue requestQueue = Volley.newRequestQueue(SummaryActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(SummaryActivity.this, "Successfully added.", Toast.LENGTH_SHORT).show();
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

    private void saveTenant() {
        SharedPreferences sharedPreferences_tenant = getSharedPreferences(AddTenant.PREF_NAME, 0);
        Tenant tenant = new Tenant();
        tenant.setId(sharedPreferences_tenant.getInt("tenant_id", 1));

        SharedPreferences sharedPreferences_house = getSharedPreferences(SelectHouse.PREF_NAME, 0);
        House house = new House();
        house.setId(sharedPreferences_house.getInt("house_id", 1));

        Occupy occupy = new Occupy();
        occupy.setTenant(tenant);
        occupy.setHouse(house);

        Gson gson = new GsonBuilder().create();
        String jsonFromObject = gson.toJson(occupy);
//        Log.d("JSON", jsonFromObject);

        Toast.makeText(SummaryActivity.this, jsonFromObject, Toast.LENGTH_SHORT).show();

        JSONObject jsonObject = new JSONObject();
        try {
            //Conversion to be verified
            jsonObject = new JSONObject(jsonFromObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "occupy/save";
//        Log.d("", url);
        RequestQueue requestQueue = Volley.newRequestQueue(SummaryActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(SummaryActivity.this, "Successfully added.", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("Error", error.toString());
                Toast.makeText(SummaryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
}
