package com.example.layout_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class OwnerDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    CardView building_card, bill_card, tenant_card, house_card;
    public static String MYPREF = "pref";
    TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);
        building_card = (CardView) findViewById(R.id.building_card);
        bill_card = (CardView) findViewById(R.id.bill_card);
        tenant_card = (CardView) findViewById(R.id.tenant_card);
        house_card = (CardView) findViewById(R.id.house_card);
        txtUsername = findViewById(R.id.txtUsername);

        building_card.setOnClickListener(this);
        bill_card.setOnClickListener(this);
        tenant_card.setOnClickListener(this);
        house_card.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
        String username = sharedPreferences.getString("user_name", "DEFAULT");
        txtUsername.setText(username);

//        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREF_NAME, 0);
//        int user_id = sharedPreferences.getInt("user_id", 1);

//        Toast.makeText(getApplicationContext(), "" + user_id, Toast.LENGTH_SHORT).show();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.owner_dashboard1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.building_card:
                bundle.putString("activity_to_open_next", "building");
                break;
            case R.id.bill_card:
                bundle.putString("activity_to_open_next", "bill");
                break;
            case R.id.tenant_card:
                bundle.putString("activity_to_open_next", "tenant");
                break;
            case R.id.house_card:
                bundle.putString("activity_to_open_next", "house");
                break;
        }
        Intent i = new Intent(this, Actions.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.notice) {

            Intent intent = new Intent(OwnerDashboard.this, NoticeActivity.class);
            startActivity(intent);

        } else if (id == R.id.edit_profile) {

        } else if (id == R.id.setting) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
