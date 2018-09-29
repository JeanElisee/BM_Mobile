package com.example.layout_module;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class RecentBills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_bills);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SpringDotsIndicator springDotsIndicator = (SpringDotsIndicator) findViewById(R.id.spring_dots_indicator);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        springDotsIndicator.setViewPager(viewPager);
    }
}
