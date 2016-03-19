package com.example.rksixers.gpstreker.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by said on 19.03.16.
 */
public class SessionManager {

    public void setPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Androidwarriors", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public  String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Androidwarriors",	Context.MODE_PRIVATE);

        return prefs.getString(key, "");
    }
}
