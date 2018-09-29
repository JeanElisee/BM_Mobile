package com.example.layout_module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTenant extends AppCompatActivity {

    Button search_tenant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant);
        search_tenant=findViewById(R.id.search_tenant);
        search_tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddTenant.this,TenantInfo.class);
                startActivity(intent);
            }
        });
    }
}
