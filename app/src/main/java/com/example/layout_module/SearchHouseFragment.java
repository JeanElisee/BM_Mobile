package com.example.layout_module;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.layout_module.Beans.Building;
import com.example.layout_module.Beans.LinkToServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SearchHouseFragment extends Fragment {

    Spinner spinner;
    Button button;
    TextView doornum, floornum, type;

    public SearchHouseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    final ArrayList<String> house_type_id = new ArrayList<String>();
    final ArrayList<String> DOORNUM = new ArrayList<String>();
    final ArrayList<String> FLOORNUM = new ArrayList<String>();
    final ArrayList<String> HOUSETYPE = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_search_house, container, false);
        spinner = view.findViewById(R.id.spinner);
        button = view.findViewById(R.id.search);
        doornum = view.findViewById(R.id.door_num);
        floornum = view.findViewById(R.id.floor_num);
        type = view.findViewById(R.id.house_type);

        //house spinner


        String url = LinkToServer.LinkDetails.SERVER_ADDRESS + "/house/";
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        house_type_id.add(jsonObject.getString("id"));
                        DOORNUM.add(jsonObject.getString("doorNo"));
                        FLOORNUM.add(jsonObject.getString("floorNo"));
                        HOUSETYPE.add(jsonObject.getString("houseType"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> charge_type_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, DOORNUM);

                spinner.setAdapter(charge_type_adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonArrayRequest);



        //sharedpreference
        Bundle bundle = this.getArguments();
        int type_id = bundle.getInt("type_id", 0);
        String lastdate = bundle.getString("lastdate", "");
        String amount = bundle.getString("amount", "");

        Toast.makeText(this.getActivity(), "" + type_id + lastdate + amount, Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doornum.setText(spinner.getSelectedItem().toString(), TextView.BufferType.EDITABLE);
                floornum.setText(FLOORNUM.get(spinner.getSelectedItemPosition()), TextView.BufferType.EDITABLE);
                type.setText(HOUSETYPE.get(spinner.getSelectedItemPosition()), TextView.BufferType.EDITABLE);
            }
        });

        return view;
    }


}
