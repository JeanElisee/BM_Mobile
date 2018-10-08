package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.layout_module.beans.Inquiry;
import com.example.layout_module.beans.LinkToServer;
import com.example.layout_module.beans.Notice;
import com.example.layout_module.beans.Owner;
import com.example.layout_module.beans.Tenant;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoticeActivity extends AppCompatActivity {

    //Notice Activity
    Button notice;
    EditText subject,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        notice=(Button)findViewById(R.id.notice);
        subject=findViewById(R.id.subject);
        message=findViewById(R.id.message);
        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        final int owner_id = sharedPreferences.getInt("user_id", 1);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notice notice = new Notice();
                Owner owner = new Owner();

                notice.setObject(subject.getText().toString());
                notice.setMessage(message.getText().toString());
                owner.setId(owner_id);
                notice.setOwner(owner);

                Gson gson = new Gson();
                String jsonObject = gson.toJson(notice);

                JSONObject jsonObject1 = new JSONObject();
                try {
                    jsonObject1 = new JSONObject(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(NoticeActivity.this, "" + jsonObject1, Toast.LENGTH_LONG).show();
                RequestQueue requestQueue = Volley.newRequestQueue(NoticeActivity.this);
                String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "notice/save";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(NoticeActivity.this, "Successful", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(NoticeActivity.this, "Not inserted", Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
