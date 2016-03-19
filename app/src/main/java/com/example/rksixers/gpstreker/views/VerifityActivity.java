package com.example.rksixers.gpstreker.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rksixers.gpstreker.R;
import com.example.rksixers.gpstreker.presenters.VerifityActivityPresenter;

public class VerifityActivity extends AppCompatActivity {

    VerifityActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifity);

        presenter = new VerifityActivityPresenter(this);
        Intent intent = getIntent();
        String request_id = intent.getStringExtra("request_id");
    }

    public void onVerifityClick(View view) {
        presenter.onVerifityButtonClick(view);
    }
}
