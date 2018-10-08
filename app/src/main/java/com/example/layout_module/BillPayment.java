package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Country;
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Tenant;

import org.json.JSONException;
import org.json.JSONObject;

public class BillPayment extends AppCompatActivity {
    Button backtoDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);
        backtoDash = findViewById(R.id.gotodash);
        //Get the bundle
        final Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final int billID = bundle.getInt("bill_id");
        //TODO: Set timer the 5sec and redirect to tenant dashboad

        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "charge/pay_now/" + billID;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "" + getString(R.string.bill_no) + billID + getString(R.string.succesfully_paid), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

        backtoDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillPayment.this, TenantDashboard.class);
                startActivity(intent);
            }
        });
    }
}
