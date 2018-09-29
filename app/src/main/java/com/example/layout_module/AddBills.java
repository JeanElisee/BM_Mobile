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

public class AddBills extends AppCompatActivity {

    Button next;
    LinearLayout linearLayout;
    TextView lastdate;
    Spinner spinner;
    EditText amount_box;
    public static String DETAILS="pref";
    public static final String PREF_NAME = "DATA";
    String name;
    int type_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bills);
        next=findViewById(R.id.next);
        linearLayout=findViewById(R.id.container);
        lastdate=findViewById(R.id.lastdate);
        spinner=findViewById(R.id.charge_type);
        amount_box=findViewById(R.id.amount);


        SharedPreferences sharedPreferences=getSharedPreferences(DETAILS,0);
        final SharedPreferences sharedPreferences1=getSharedPreferences(Login.PREF_NAME,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("type_id", String.valueOf(type_id));
        editor.putString("lastdate",lastdate.getText().toString());
        editor.putString("amount",amount_box.getText().toString());
        editor.apply();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Charge charge=new Charge();
                charge.setId(Integer.parseInt(sharedPreferences1.getString("user_id","")));
                charge.setLastDate(lastdate.getText().toString());
                charge.setAmount(amount_box.getText().toString());
                ChargeType chargeType=new ChargeType();
                chargeType.setId(spinner.getId());
                chargeType.setName(spinner.getSelectedItem().toString());
                charge.setChargeType(chargeType);

                Intent intent=new Intent(AddBills.this,SelectBuilding.class);
                final SharedPreferences  mPrefs = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(charge);
                prefsEditor.putString("chargeObject", json);
                prefsEditor.apply();
                startActivity(intent);
            }
        });

        //Date format//
        final SimpleDateFormat lastdateformat = new SimpleDateFormat("yyyy-MM-dd");
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

        final ArrayList<String> charge_type=new ArrayList<String>();
        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/charge/type/" ;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               for(int i=0;i<response.length();i++)
               {
                   try {
                       JSONObject jsonObject=response.getJSONObject(i);
                       name=jsonObject.getString("name");
                       type_id=jsonObject.getInt("id");
                       charge_type.add(name);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
                ArrayAdapter<String> charge_type_adapter=new ArrayAdapter<String>(AddBills.this,android.R.layout.simple_spinner_dropdown_item,charge_type);
                spinner.setAdapter(charge_type_adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}
