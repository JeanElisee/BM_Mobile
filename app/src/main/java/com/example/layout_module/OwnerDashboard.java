package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OwnerDashboard extends AppCompatActivity implements View.OnClickListener {

    CardView building_card, bill_card, tenant_card, house_card;
    TextView txtUsername;
    public static String MYPREF = "pref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_dashboard);
        building_card = (CardView) findViewById(R.id.building_card);
        bill_card = (CardView) findViewById(R.id.bill_card);
        tenant_card = (CardView) findViewById(R.id.tenant_card);
        house_card = (CardView) findViewById(R.id.house_card);
        txtUsername = findViewById(R.id.txtUsername);

        building_card.setOnClickListener(this);
        bill_card.setOnClickListener(this);
        tenant_card.setOnClickListener(this);
        house_card.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        String username = sharedPreferences.getString("user_name", "DEFAULT");
        txtUsername.setText(username);

    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.building_card:
                bundle.putString("activity_to_open_next", "building");
                break;
            case R.id.bill_card:
                bundle.putString("activity_to_open_next", "bill");
                break;
            case R.id.tenant_card:
                bundle.putString("activity_to_open_next", "tenant");
                break;
            case R.id.house_card:
                bundle.putString("activity_to_open_next", "house");
                break;
        }
        Intent i = new Intent(this, Actions.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
