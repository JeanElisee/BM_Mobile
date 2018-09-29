package com.example.layout_module;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HouseDetails extends AppCompatActivity {

    Button next;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        next=findViewById(R.id.next);
        linearLayout=findViewById(R.id.container);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayout.removeAllViews();
                SearchBuildingFragment fragmentOne=new SearchBuildingFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container,fragmentOne).commit();
            }
        });
    }
}
