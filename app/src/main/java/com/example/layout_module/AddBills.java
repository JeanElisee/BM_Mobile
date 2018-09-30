package com.example.layout_module;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.beans.Charge;
import com.example.layout_module.beans.ChargeType;
import com.example.layout_module.beans.LinkToServer;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddBills extends AppCompatActivity {

    Button btnNext; // Clear name so that no confusion
    LinearLayout linearLayout;
    TextView lastdate;
    Spinner spinner;
    EditText amount_box;
    public static final String PREF_NAME = "bill_info";


    // If final create runtime error, bcz cannot change
    ArrayList<String> chargeTypeName = new ArrayList<String>();
    ArrayList<Integer> chargeTypeId = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bills);

        btnNext = findViewById(R.id.next);
        linearLayout = findViewById(R.id.container);
        lastdate = findViewById(R.id.lastdate);
        spinner = findViewById(R.id.charge_type);
        amount_box = findViewById(R.id.amount);


        //Retrieve charge type and store in the dropdown list
        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/charge/type/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        chargeTypeId.add(jsonObject.getInt("id"));
                        chargeTypeName.add(jsonObject.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> charge_type_adapter = new ArrayAdapter<String>(AddBills.this, android.R.layout.simple_spinner_dropdown_item, chargeTypeName);
                spinner.setAdapter(charge_type_adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


//Get Date from time picker
        final SimpleDateFormat lastdateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                lastdate.setText(lastdateformat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        lastdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime.show();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Only after clicking on next we need to update
                SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("charge_type_id", chargeTypeId.get(spinner.getSelectedItemPosition()));
                editor.putString("charge_last_date", lastdate.getText().toString());
                editor.putString("charge_amount", amount_box.getText().toString());
                editor.apply();

                Intent intent = new Intent(AddBills.this, SelectBuilding.class);
                startActivity(intent);
            }
        });
    }
}
