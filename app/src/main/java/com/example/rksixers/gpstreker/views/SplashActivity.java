package com.example.rksixers.gpstreker.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.rksixers.gpstreker.R;
import com.example.rksixers.gpstreker.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionManager = new SessionManager();

        new Handler().postDelayed(() -> {
            String status = sessionManager.getPreferences(SplashActivity.this, "Status");
            if (status.equals("1")) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }
            finish();
        }, SPLASH_TIME_OUT);
    }
}
