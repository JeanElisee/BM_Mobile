package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.LinkToServer;

import org.json.JSONException;
import org.json.JSONObject;

public class AddTenant extends AppCompatActivity {

    Button search_tenant;
    EditText txtPhoneNum;
    public static final String PREF_NAME = "tenant_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant);
        search_tenant = findViewById(R.id.search_tenant);

        txtPhoneNum = findViewById(R.id.phone_num);

        search_tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tenantPhoneNumber = txtPhoneNum.getText().toString();

                if (tenantPhoneNumber.length() < 10 || tenantPhoneNumber.length() > 10) {
                    Toast.makeText(getApplicationContext(), "Phone Number Should Have 10 digits", Toast.LENGTH_SHORT).show();
                } else {
                    final String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/tenant/get_by_phone/" + tenantPhoneNumber;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
//                                    ============< Take Country information >=============
                                JSONObject countryJson = response.getJSONObject("tenantCountry");

                                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt("tenant_id", response.getInt("id"));
                                editor.putString("tenant_firstname", response.getString("firstName"));
                                editor.putString("tenant_lastname", response.getString("lastName"));
                                editor.putString("tenant_email", response.getString("mailId"));
                                editor.putString("tenant_phone_number", response.getString("phoneNumber"));
                                editor.putString("tenant_date_of_birth", response.getString("dateOfBirth"));
                                editor.putString("tenant_country", countryJson.getString("iso_code"));
                                editor.apply();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(AddTenant.this, TenantInfo.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "User Does Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );
                    RequestQueue requestQueue = Volley.newRequestQueue(AddTenant.this);
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });
    }
}
