package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Country;
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Owner;
import com.example.layout_module.beans.Tenant;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView login, sign_up;
    RadioButton tenant, owner;
    EditText mailId, password;
    String email, password_text;
    public static final String PREF_NAME = "login_information";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        tenant = findViewById(R.id.tenant);
        owner = findViewById(R.id.owner);
        sign_up = findViewById(R.id.sign_up);
        mailId = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mailId.getText().toString();
                password_text = password.getText().toString();

                if (tenant.isChecked()) {
                    String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "tenant/login/" + email + "/" + password_text;
                    JsonParse(url, "tenant");

                } else if (owner.isChecked()) {
                    String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "owner/login/" + email + "/" + password_text;
                    JsonParse(url, "owner");
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void JsonParse(String url, final String whichOne) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (whichOne.equals("tenant")) {
                    try {
//                      ============< Take Tenant information >===============
                        Tenant tenant = new Tenant();

                        tenant.setId(response.getInt("id"));
                        tenant.setFirstName(response.getString("firstName"));
                        tenant.setLastName(response.getString("lastName"));
                        tenant.setGender(response.getString("gender"));
                        tenant.setMailId(response.getString("mailId"));
                        tenant.setPhoneNumber(response.getString("phoneNumber"));
                        tenant.setDateOfBirth(response.getString("dateOfBirth"));
                        tenant.setRegistrationDate(response.getString("registrationDate"));
                        tenant.setUsername(response.getString("username"));
                        tenant.setPassword(response.getString("password"));

//                      ============< Take Country information >=============
                        JSONObject countryJson = response.getJSONObject("tenantCountry");

                        Country country = new Country();

                        country.setIso_code(countryJson.getString("iso_code"));
                        country.setName(countryJson.getString("name"));
                        country.setIsd_code(countryJson.getString("isd_code"));

                        tenant.setTenantCountry(country);

                        Log.d("", tenant.toString());
                        Toast.makeText(getApplicationContext(), tenant.toString(), Toast.LENGTH_SHORT).show();


                        editor.putInt("user_id", tenant.getId());
                        editor.putString("user_name", tenant.getFirstName() + " " + tenant.getLastName());

                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), TenantDashboard.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (whichOne.equals("owner")) {
                    try {
//                      ============< Take Tenant information >===============
                        Owner owner = new Owner();

                        owner.setId(response.getInt("id"));
                        owner.setFirstName(response.getString("firstName"));
                        owner.setLastName(response.getString("lastName"));
                        owner.setGender(response.getString("gender"));
                        owner.setMailId(response.getString("mailId"));
                        owner.setPhoneNumber(response.getString("phoneNumber"));
                        owner.setDateOfBirth(response.getString("dateOfBirth"));
                        owner.setRegistrationDate(response.getString("registrationDate"));
                        owner.setUsername(response.getString("username"));
                        owner.setPassword(response.getString("password"));

//                      ============< Take Country information >=============
                        JSONObject countryJson = response.getJSONObject("ownerCountry");

                        Country country = new Country();

                        country.setIso_code(countryJson.getString("iso_code"));
                        country.setName(countryJson.getString("name"));
                        country.setIsd_code(countryJson.getString("isd_code"));

                        owner.setOwnerCountry(country);

                        editor.putInt("user_id", owner.getId());
                        editor.putString("user_name", owner.getFirstName() + " " + owner.getLastName());
                        editor.apply();

                        Intent profile_activity = new Intent(getApplicationContext(), OwnerDashboard.class);
                        startActivity(profile_activity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Wrong password or username", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}