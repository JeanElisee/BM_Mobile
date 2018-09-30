package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.House;
import com.example.layout_module.beans.HouseType;
import com.example.layout_module.beans.LinkToServer;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectHouse extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> floorList = new ArrayList<>();
    ;
    TextView textView_floor;
    Button next;
    Bundle data;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_house);
        spinner = findViewById(R.id.floor_num_spinner);
        textView_floor = findViewById(R.id.textViewFloor);
        next = findViewById(R.id.next);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int noOfFloor = bundle.getInt("no_of_floor");


        for (int i = 0; i < noOfFloor; i++) {
            floorList.add("00" + i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SelectHouse.this, android.R.layout.simple_spinner_dropdown_item, floorList);
        spinner.setAdapter(arrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectHouse.this, SummaryActivity.class);
                startActivity(intent);
            }
        });


    }
}
