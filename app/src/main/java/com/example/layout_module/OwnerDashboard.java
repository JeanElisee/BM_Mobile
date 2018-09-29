package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class OwnerDashboard extends AppCompatActivity implements View.OnClickListener

{

    CardView card1,card2,card3,card4;
    public static String MYPREF="pref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_dashboard);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);
        card3=(CardView)findViewById(R.id.card3);
        card4=(CardView)findViewById(R.id.card4);

        //Session building,house //

       
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        SharedPreferences sharedPreferences=getSharedPreferences(MYPREF,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        switch (view.getId())
        {
            case R.id.card1:
                editor.putString("activity","building");
                Toast.makeText(OwnerDashboard.this,""+sharedPreferences.getString("building","building"),Toast.LENGTH_SHORT).show();
                break;
            case R.id.card2:
                editor.putString("activity","bill");
                Toast.makeText(OwnerDashboard.this,""+sharedPreferences.getString("building","building"),Toast.LENGTH_SHORT).show();
                break;
            case R.id.card4:
                editor.putString("activity","house");
                Toast.makeText(OwnerDashboard.this,""+sharedPreferences.getString("house","house"),Toast.LENGTH_SHORT).show();
                break;
        }
        editor.apply();
        Intent i=new Intent(this,Actions.class);
        startActivity(i);
    }
}
