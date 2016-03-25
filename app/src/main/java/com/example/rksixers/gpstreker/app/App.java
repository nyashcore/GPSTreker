package com.example.rksixers.gpstreker.app;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by said on 25.03.16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        VKSdk.initialize(this);
    }
}
