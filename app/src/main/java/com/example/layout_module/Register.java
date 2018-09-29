package com.example.layout_module;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.Beans.Country;
import com.example.layout_module.Beans.LinkToServer;
import com.example.layout_module.Beans.Tenant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextView txtLogin, txtSignup;
    EditText txtEmail, txtUsername, txtPassword, txtFirstName, txtLastName, txtPhoneNumber;
    TextView txtDateOfBirth;
    Spinner spnGender, spnCountry;

    ArrayList<String> countryIsoCode = new ArrayList<String>();
    ArrayList<String> countryName = new ArrayList<String>();
    ArrayList<String> countryIsdCode = new ArrayList<String>();

    public static final String TAG = Register.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final SimpleDateFormat dateofbirthformat = new SimpleDateFormat("yyyy-MM-dd");

        txtFirstName = findViewById(R.id.firstname);
        txtLastName = findViewById(R.id.lastname);
        txtLogin = findViewById(R.id.login);
        txtEmail = findViewById(R.id.email);
        txtPhoneNumber = findViewById(R.id.phone);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        txtDateOfBirth = findViewById(R.id.dob);

        spnGender = findViewById(R.id.spinner);
        spnCountry = findViewById(R.id.country_spinner);
        txtSignup = findViewById(R.id.sign_up);

        RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

        String[] item = new String[]{"Female", "Male", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, item);
        spnGender.setAdapter(adapter);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtDateOfBirth.setText(dateofbirthformat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        txtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime.show();
            }
        });


        final String country_url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/country/";

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, country_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        countryIsoCode.add(jsonObject.getString("iso_code"));
                        countryName.add(jsonObject.getString("name"));
                        countryIsdCode.add(jsonObject.getString("isd_code"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_dropdown_item, countryName);
                spnCountry.setAdapter(adapter1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Not able to fetch countries.", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);


        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tenant tenant = new Tenant();

                tenant.setFirstName(txtFirstName.getText().toString());
                tenant.setLastName(txtLastName.getText().toString());
                tenant.setLastName(txtLastName.getText().toString());
                tenant.setGender(spnGender.getSelectedItem().toString());
                tenant.setMailId(txtEmail.getText().toString());
                tenant.setPhoneNumber(txtPhoneNumber.getText().toString());
                tenant.setDateOfBirth(txtDateOfBirth.getText().toString());
                tenant.setUsername(txtUsername.getText().toString());
                tenant.setPassword(txtPassword.getText().toString());

                Country country = new Country();

                country.setIso_code(countryIsoCode.get(spnCountry.getSelectedItemPosition()));
                country.setName(spnCountry.getSelectedItem().toString());
                country.setIsd_code(countryIsdCode.get(spnCountry.getSelectedItemPosition()));

                tenant.setTenantCountry(country);

                Gson gson = new GsonBuilder().create();
                String jsonFromObject = gson.toJson(tenant);
                Log.d("JSON", jsonFromObject);

                JSONObject jsonObject = new JSONObject();
                try {
                    //Conversion to be verified
                    jsonObject = new JSONObject(jsonFromObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/tenant/save";

                RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Register.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}