package com.example.layout_module;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Inquiry;
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Tenant;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InquiryActivity extends AppCompatActivity {

    Button save;
    EditText subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);
        save = findViewById(R.id.save);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        Date current_date = Calendar.getInstance().getTime();
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String date = df.format(current_date);

        final int tenant_id = sharedPreferences.getInt("user_id", 1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inquiry inquiry = new Inquiry();
                Tenant tenant = new Tenant();

                inquiry.setObject(subject.getText().toString());
                inquiry.setMessage(message.getText().toString());
                tenant.setId(tenant_id);
                inquiry.setTenant(tenant);

                Gson gson = new Gson();
                String jsonObject = gson.toJson(inquiry);

                JSONObject jsonObject1 = new JSONObject();
                try {
                    jsonObject1 = new JSONObject(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(InquiryActivity.this, "" + jsonObject1, Toast.LENGTH_LONG).show();
                RequestQueue requestQueue = Volley.newRequestQueue(InquiryActivity.this);
                String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/inquiry/save";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(InquiryActivity.this, "Successful", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(InquiryActivity.this, "Not inserted", Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
