package com.example.layout_module;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout_module.beans.Building;
import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.ChargeType;
import com.example.layout_module.beans.House;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        textView1 = findViewById(R.id.charge);
        textView2 = findViewById(R.id.building);
        textView3 = findViewById(R.id.house);


        SharedPreferences sharedPreferences_bill = getSharedPreferences(AddBills.PREF_NAME, 0);

        Charge charge = new Charge();
        charge.setAmount(sharedPreferences_bill.getString("charge_amount", "DEFAULT"));
        charge.setLastDate(sharedPreferences_bill.getString("charge_last_date", "DEFAULT"));

        ChargeType chargeType = new ChargeType();
        chargeType.setId(sharedPreferences_bill.getInt("charge_type_id", 1));

        charge.setChargeType(chargeType);

        SharedPreferences sharedPreferences_house = getSharedPreferences(SelectHouse.PREF_NAME, 0);
        House house = new House();
        house.setId(sharedPreferences_bill.getInt("house_id", 1));

        charge.setHouse(house);

        Gson gson = new GsonBuilder().create();
        String jsonFromObject = gson.toJson(charge);
        Log.d("JSON", jsonFromObject);

        Toast.makeText(getApplicationContext(), "" + jsonFromObject, Toast.LENGTH_LONG).show();
        Log.d("", jsonFromObject);
    }
}
