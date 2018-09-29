package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Actions extends AppCompatActivity {

    ImageView add;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        add = findViewById(R.id.add);

        final SharedPreferences sharedPreferences = getSharedPreferences(OwnerDashboard.MYPREF, 0);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String activity = sharedPreferences.getString("activity", "");
                //Get the bundle
                Bundle bundle = getIntent().getExtras();

                Intent intent = new Intent();

//Extract the data…
                assert bundle != null;
                String activityToOpenNext = bundle.getString("activity_to_open_next");

                Log.d("Which activity :", activityToOpenNext);

                switch (activityToOpenNext) {
                    case "house":
                        intent = new Intent(Actions.this, HouseDetails.class);
                        //add_house.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Toast.makeText(Actions.this, "" + sharedPreferences.getString("house", "house"), Toast.LENGTH_SHORT).show();
                        break;

                    case "building":
                        intent = new Intent(Actions.this, AddBuildings.class);
//                    Toast.makeText(Actions.this, "" + sharedPreferences.getString("building", "building"), Toast.LENGTH_SHORT).show();
                        break;

                    case "bill":
                        intent = new Intent(Actions.this, AddBills.class);
                        //add_house.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Toast.makeText(Actions.this, "" + sharedPreferences.getString("house", "house"), Toast.LENGTH_SHORT).show();
                        break;
                        
                    default:
                        intent = new Intent(Actions.this, AddTenant.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
