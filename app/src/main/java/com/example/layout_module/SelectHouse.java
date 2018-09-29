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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.Beans.Building;
import com.example.layout_module.Beans.Charge;
import com.example.layout_module.Beans.House;
import com.example.layout_module.Beans.HouseType;
import com.example.layout_module.Beans.LinkToServer;
import com.example.layout_module.Beans.Owner;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectHouse extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> floor_num;
    TextView textView_floor;
    Button next;
    Bundle data;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_house);
        spinner=findViewById(R.id.floor_num_spinner);
        textView_floor=findViewById(R.id.textViewFloor);
        next=findViewById(R.id.next);
        floor_num=new ArrayList<>();
        data=getIntent().getExtras();
        prefs=getSharedPreferences(Login.PREF_NAME,0);
        if(data!=null)
        {
            floor_num.add("Ground Floor");
            for(int i=0;i<Integer.parseInt(data.getString("floornum"));i++)
            {
                floor_num.add("00"+String.valueOf(i));
            }
            floor_num.add("00"+data.getString("floornum"));
            Log.d("tag",data.toString());
        }

        //charge,building bundle
       /* final Charge charge=(Charge)getIntent().getSerializableExtra("chargeObject");
        Building building=(Building) getIntent().getSerializableExtra("buildingObject");
        Toast.makeText(this,""+charge.getAmount() +building.getNoFloor(),Toast.LENGTH_LONG).show();*/

        //charge,building bundle
        final SharedPreferences  mPrefs = getSharedPreferences(AddBills.PREF_NAME,MODE_PRIVATE);
        final ArrayList<Charge> chargeArrayList = new ArrayList<>();
        final ArrayList<Building> buildingArrayList = new ArrayList<>();
        final ArrayList<House> houseArrayList = new ArrayList<>();
        Gson gson = new Gson();
        String chargejson = mPrefs.getString("chargeObject","");
        String buildingjson = mPrefs.getString("buildingObject","");
        final Charge charge = gson.fromJson(chargejson, Charge.class);
        final Building building=gson.fromJson(buildingjson,Building.class);
        chargeArrayList.add(charge);
        buildingArrayList.add(building);

        final House house = new House();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectHouse.this,SummaryActivity.class);
                final SharedPreferences  mPrefs = getSharedPreferences(AddBills.PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String chargejson = gson.toJson(charge);
                String buildingjson = gson.toJson(building);
                String housejson = gson.toJson(house);
                prefsEditor.putString("chargeObject", chargejson);
                prefsEditor.putString("buildingOject", buildingjson);
                prefsEditor.putString("houseObject", housejson);
                prefsEditor.apply();
                startActivity(intent);
            }
        });

        String user_id=prefs.getString("user_id","");
        Toast.makeText(this,"USer ID"+user_id,Toast.LENGTH_LONG).show();
        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/house/"+user_id;
        RequestQueue requestQueue = Volley.newRequestQueue(SelectHouse.this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    house.setId(response.getInt("id"));
                    house.setDoorNo(response.getString("doorNo"));
                    house.setFloorNo(response.getString("floorNo"));
                    house.setRentAmount(response.getString("rentAmount"));
                    house.setDeposit(response.getString("deposit"));
                    house.setOccupationType(response.getInt("occupationType"));

                    Building building=new Building();
                    building.setId(response.getInt("building"));

                    HouseType houseType=new HouseType();
                    houseType.setId(response.getInt("houseType"));

                    house.setBuilding(building);
                    house.setHouseType(houseType);
                    houseArrayList.add(house);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonObjectRequest);


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(SelectHouse.this,android.R.layout.simple_spinner_dropdown_item,floor_num);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView_floor.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
