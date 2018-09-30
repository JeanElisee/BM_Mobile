package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.LinkToServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddHouse extends AppCompatActivity {

    Button next;
    LinearLayout linearLayout;
    EditText txtDoorNo, txtRentAmount, txtDeposit;
    Spinner spinner;

    public static final String PREF_NAME = "house_information";

    // If final create runtime error, bcz cannot change
    ArrayList<Integer> houseTypeId = new ArrayList<Integer>();
    ArrayList<String> houseType = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);
        next = findViewById(R.id.next);
        linearLayout = findViewById(R.id.container);

        spinner = findViewById(R.id.house_type);
        txtDoorNo = findViewById(R.id.txtDoorNo);
        txtRentAmount = findViewById(R.id.txtRentAmount);
        txtDeposit = findViewById(R.id.txtDeposit);


        //Retrieve charge type and store in the dropdown list
        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/house/type/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        houseTypeId.add(jsonObject.getInt("id"));
                        houseType.add(jsonObject.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> charge_type_adapter = new ArrayAdapter<String>(AddHouse.this, android.R.layout.simple_spinner_dropdown_item, houseType);
                spinner.setAdapter(charge_type_adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("h_door_no", txtDoorNo.getText().toString());
                editor.putString("h_rent_amount", txtRentAmount.getText().toString());
                editor.putString("h_deposit", txtDeposit.getText().toString());
                editor.putInt("h_type", spinner.getSelectedItemPosition());
                editor.apply();

                Intent intent = new Intent(AddHouse.this, SelectBuilding.class);
                startActivity(intent);
            }
        });
    }
}
