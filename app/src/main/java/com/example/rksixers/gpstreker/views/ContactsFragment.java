package com.example.rksixers.gpstreker.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rksixers.gpstreker.R;

public class ContactsFragment extends Fragment {

    String[] numbers_text = new String[]{"one", "two", "three", "four"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false);
    }
}
