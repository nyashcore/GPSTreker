package com.example.rksixers.gpstreker.presenters;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rksixers.gpstreker.views.LoginActivity;
import com.example.rksixers.gpstreker.views.VerifityActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

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
    VerifityTask verifitytask;
    EditText phoneEditText;
    public static final String API_KEY = "8b2389bd";
    public static final String API_SECRET = "fa36ba6ff8ec5928";
    public static final String SMS_FROM = "GPS_Treker";
    Intent intent;

    public LoginActivityPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    public void onSignInClick(View view) {
        phoneEditText = (EditText) view;

        verifitytask = new VerifityTask();
        String url = "https://api.nexmo.com/verify/json" +
                "?api_key=" +
                API_KEY +
                "&api_secret=" +
                API_SECRET +
                "&number=" +
                ((EditText) view).getText().toString() +
                "&brand=" +
                SMS_FROM;
        verifitytask.execute(url);
    }

    class VerifityTask extends AsyncTask<String, Void, JsonObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JsonObject doInBackground(String... urls) {
            HttpsURLConnection connection = null;

            try {
                URL url = new URL(urls[0]);

                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int status = connection.getResponseCode();
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            connection.getInputStream()
                    ));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();
                } catch (IOException e) {
                    return null;
                }
                JsonObject jsonObject;

                try {
                    jsonObject  = new Gson().fromJson(stringBuilder.toString(), JsonObject.class);
                } catch (JsonSyntaxException e) {
                    return null;
                }
                return jsonObject;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JsonObject result) {
            super.onPostExecute(result);

            activity.openVerifityActivity(result.get("request_id").getAsString());
        }
    }
}
