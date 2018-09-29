package com.example.layout_module;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TenantDashboard extends AppCompatActivity implements View.OnClickListener {
    ImageView profile;
    FrameLayout bills, sample1, sample2, inquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_dashboard);
        bills = (FrameLayout) findViewById(R.id.image1);
        sample1 = (FrameLayout) findViewById(R.id.image2);
        sample2 = (FrameLayout) findViewById(R.id.image3);
        inquiry = (FrameLayout) findViewById(R.id.image4);

        bills.setOnClickListener(this);
        sample1.setOnClickListener(this);
        sample2.setOnClickListener(this);
        inquiry.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Actions.class);
        startActivity(i);
    }
}
