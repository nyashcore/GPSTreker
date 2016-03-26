package com.example.rksixers.gpstreker.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by said on 19.03.16.
 */
public class SessionManager {

    public void setPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("UserPref", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public  String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("UserPref",	Context.MODE_PRIVATE);
        String value = prefs.getString(key, "default");

        return value;
    }
}
