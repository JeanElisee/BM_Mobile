package com.example.layout_module;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout_module.Beans.Building;
import com.example.layout_module.Beans.Charge;
import com.example.layout_module.Beans.House;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        textView1=findViewById(R.id.charge);
        textView2=findViewById(R.id.building);
        textView3=findViewById(R.id.house);

        final SharedPreferences mPrefs = getSharedPreferences(AddBills.PREF_NAME,MODE_PRIVATE);
        final ArrayList<Charge> chargeList = new ArrayList<>();
        final ArrayList<Building> buildingList = new ArrayList<>();
        final ArrayList<House> houseList = new ArrayList<>();
        Gson gson = new Gson();
        String chargejson = mPrefs.getString("chargeObject","");
        String buildingjson = mPrefs.getString("buildingObject","");
        String housejson = mPrefs.getString("houseObject","");
        Charge charge = gson.fromJson(chargejson, Charge.class);
        Building building = gson.fromJson(buildingjson, Building.class);
        House house = gson.fromJson(housejson, House.class);
        chargeList.add(charge);
        buildingList.add(building);
        houseList.add(house);
        Toast.makeText(SummaryActivity.this,""+chargejson +buildingjson +housejson,Toast.LENGTH_LONG).show();



        textView1.setText("Amount "+charge.getAmount()+"\n"+"Lastdate "+charge.getLastDate()+"\n"+"ChargeType "+charge.getChargeType());


        textView2.setText("Name "+building.getName()+"\n"+"Address "+building.getAddress()
                +"\n"+"No of Floors "+building.getNoFloor()+"\n"+"Owner "+building.getOwner());


        textView3.setText("Deposit "+house.getDeposit()+"\n"+"Door number "+house.getDoorNo()+"\n"
                +"Floor Number "+house.getFloorNo()+"\n"+"Rent Amount "+house.getRentAmount()+"\n"+
        "Occupation Type "+house.getOccupationType()+"\n"+"House Type "+house.getHouseType());
    }
}
