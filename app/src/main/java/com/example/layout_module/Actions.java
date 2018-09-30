package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Actions extends AppCompatActivity {
    ImageView add;
    public static final String PREF_ACTION = "action_to_be_done";

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

                SharedPreferences sharedPreferences_action = getSharedPreferences(PREF_ACTION, 0);
                SharedPreferences.Editor editor_action = sharedPreferences_action.edit();

                switch (activityToOpenNext) {
                    case "house":
                        intent = new Intent(Actions.this, AddHouse.class);
                        editor_action.putString("action", "house");
                        break;

                    case "building":
                        intent = new Intent(Actions.this, AddBuildings.class);
                        editor_action.putString("action", "building");
                        break;

                    case "bill":
                        intent = new Intent(Actions.this, AddBills.class);
                        editor_action.putString("action", "bill");
                        break;

                    default:
                        intent = new Intent(Actions.this, AddTenant.class);
                        editor_action.putString("action", "tenant");
                        break;
                }
                editor_action.apply();
                startActivity(intent);
            }
        });
    }
}
