package com.example.rksixers.gpstreker.views;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.rksixers.gpstreker.utils.MyAdapter;
import com.example.rksixers.gpstreker.R;


public class MainActivity extends AppCompatActivity implements MyAdapter.ViewHolder.ClickListener {
    String TITLES[] = {"Оповещения", "Контакты", "Войти", "Настройки"};
    int ICONS[] = {R.drawable.ic_warning, R.drawable.ic_contacts, R.drawable.ic_signin, R.drawable.ic_settings};
    String NAME = "Said";
    String EMAIL = "g.said.alievich@mail.ru";
    int PROFILE = R.drawable.logo;
    private Toolbar toolbar;
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(this, TITLES,ICONS,NAME,EMAIL,PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.open_drawer,R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onItemClicked(int position) {
        Fragment fragment = null;

        switch (position) {
            case 3:
                break;
            default:
                break;
        }

        if (fragment != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            Drawer.closeDrawer(mRecyclerView);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Фрагмент еще не существует", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onSignUpClick(View view) {
        Fragment fragment = new SignupFragment();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}