package com.example.layout_module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout_module.beans.Charge;

public class BillDetail extends AppCompatActivity {
    TextView txtBillAmount, txtBillHouseId, txtBillType, txtBillLastDate;
    ImageView payNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        txtBillAmount = findViewById(R.id.txtBillsAmount);
        txtBillHouseId = findViewById(R.id.txtHouseId);
        txtBillType = findViewById(R.id.txtBillsType);
        txtBillLastDate = findViewById(R.id.txtBillsLastDate);
        payNow = findViewById(R.id.btnPayNow);


        //Get the bundle
        final Bundle bundle = getIntent().getExtras();


        Charge charge = new Charge();
        assert bundle != null;
        charge.setId(bundle.getInt("bill_id"));
        charge.setAmount(bundle.getString("bill_amount"));
        charge.setLastDate(bundle.getString("bill_last_date"));
        charge.setAddedOn(bundle.getString("bill_added_on"));

        txtBillAmount.setText(charge.getAmount());
//        txtBillHouseId.setText(charge.getHouse());
//        txtBillType.setText(charge.getChargeType());
        txtBillLastDate.setText(charge.getLastDate());

        final Bundle bundle_send = new Bundle();
        bundle_send.putInt("bill_id", charge.getId());

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillDetail.this, BillPayment.class); //change layout
                intent.putExtras(bundle_send);
                startActivity(intent);
            }
        });
    }
}
