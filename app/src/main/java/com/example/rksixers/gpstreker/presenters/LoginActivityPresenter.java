package com.example.rksixers.gpstreker.presenters;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rksixers.gpstreker.views.LoginActivity;
import com.example.rksixers.gpstreker.views.VerifityActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by said on 19.03.16.
 */
public class LoginActivityPresenter {
    LoginActivity activity;
    EditText phoneEditText;
    private SpiceManager spiceManager;
    public static final String API_KEY = "8b2389bd";
    public static final String API_SECRET = "fa36ba6ff8ec5928";
    public static final String SMS_FROM = "GPS_Treker";
    Intent intent;

    public LoginActivityPresenter(LoginActivity activity, SpiceManager spiceManager) {
        this.activity = activity;
        this.spiceManager = spiceManager;
    }

    public void onSignInClick(View view) {
        phoneEditText = (EditText) view;

        String url = "https://api.nexmo.com/verify/json" +
                "?api_key=" +
                API_KEY +
                "&api_secret=" +
                API_SECRET +
                "&number=" +
                ((EditText) view).getText().toString() +
                "&brand=" +
                SMS_FROM;
        sendRequest(url, spiceManager);
    }

    private void sendRequest(String url, SpiceManager spiceManager) {
        SimpleTextRequest request = new SimpleTextRequest(url);

        spiceManager.execute(request, "jsonGPSTreker", DurationInMillis.ONE_SECOND, new GPSTrekerApiJsonRequestListener());
    }

    public final class GPSTrekerApiJsonRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            //TODO
            Log.i("Presenter", "Request failed");
        }

        @Override
        public void onRequestSuccess(String s) {
            Log.i("Presenter", "Request success: " + s);
            JsonObject jsonObject;

            try {
                jsonObject  = new Gson().fromJson(s, JsonObject.class);
            } catch (JsonSyntaxException e) {
                //TODO
            }
        }
    }
}
