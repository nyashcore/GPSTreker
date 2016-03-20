package com.example.rksixers.gpstreker.views;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rksixers.gpstreker.R;
import com.example.rksixers.gpstreker.network.GPSTrekerService;
import com.example.rksixers.gpstreker.presenters.LoginActivityPresenter;
import com.octo.android.robospice.SpiceManager;

public class LoginActivity extends AppCompatActivity {
    LoginActivityPresenter presenter;
    EditText phoneEditText;
    protected SpiceManager spiceManager = new SpiceManager(GPSTrekerService.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spiceManager.start(this);

        presenter = new LoginActivityPresenter(this, spiceManager);
        phoneEditText = (EditText) findViewById(R.id.input_phone);
    }

    public void onSignInClick(View view) {
        presenter.onSignInClick(phoneEditText);
    }

    public void openVerifityActivity(String param) {
        Intent intent = new Intent(this, VerifityActivity.class);

        intent.putExtra("request_id", param);
        startActivity(intent);
    }
}
