package com.example.layout_module.beans;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setId(int id) {
        prefs.edit().putInt("userId", id).apply();
    }

    public int getUserId() {
        return prefs.getInt("userId", 1);
    }
}