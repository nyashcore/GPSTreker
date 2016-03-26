package com.example.rksixers.gpstreker.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rksixers.gpstreker.R;
import com.example.rksixers.gpstreker.utils.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class OriginalSettingsActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    EditText editText;
    ImageView imageView;
    SessionManager sessionManager;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_settings);

        editText = (EditText) findViewById(R.id.input_name);
        imageView = (ImageView) findViewById(R.id.profile_image);
        sessionManager = new SessionManager();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imageView.setImageBitmap(bitmap);
                sessionManager.setPreferences(OriginalSettingsActivity.this, "Image", BitMapToString(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onButtonStartUse(View view) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(OriginalSettingsActivity.this, "Введите имя", Toast.LENGTH_SHORT).show();
        } else {
            sessionManager.setPreferences(OriginalSettingsActivity.this, "Status", "1");
            sessionManager.setPreferences(OriginalSettingsActivity.this, "Image", BitMapToString(bitmap));
            sessionManager.setPreferences(OriginalSettingsActivity.this, "Name", editText.getText().toString());
            startActivity(new Intent(OriginalSettingsActivity.this, MainActivity.class));
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
        byte[] b = ByteStream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);

        return temp;
    }

    public void onButtonPickPhoto(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
}
