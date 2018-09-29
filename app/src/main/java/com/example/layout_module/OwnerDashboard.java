package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class OwnerDashboard extends AppCompatActivity implements View.OnClickListener

{

    CardView card1, card2, card3, card4;
    public static String MYPREF = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_dashboard);
        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        card4 = (CardView) findViewById(R.id.card4);

        //Session building,house //


        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.card1:
                bundle.putString("activity_to_open_next", "building");
                break;
            case R.id.card2:
                bundle.putString("activity_to_open_next", "bill");
                break;
            case R.id.card3:
                bundle.putString("activity_to_open_next", "tenant");
                break;
            case R.id.card4:
                bundle.putString("activity_to_open_next", "house");
                break;
        }
        Intent i = new Intent(this, Actions.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
