package com.example.rksixers.gpstreker.views;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rksixers.gpstreker.utils.MyAdapter;
import com.example.rksixers.gpstreker.R;
import com.example.rksixers.gpstreker.utils.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements MyAdapter.ViewHolder.ClickListener {

    String TITLES[] = {"Оповещения", "Контакты", "Войти", "Настройки"};
    int ICONS[] = {R.drawable.ic_warning, R.drawable.ic_contacts, R.drawable.ic_signin, R.drawable.ic_settings};
    String NAME;
    Bitmap PROFILE;
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    CircleImageView circleImageView;
    SessionManager sessionManager;
    TextView name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager();
        name = (TextView) findViewById(R.id.name);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        circleImageView = (CircleImageView) findViewById(R.id.circle__image_view);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        NAME = sessionManager.getPreferences(MainActivity.this, "Name");
        PROFILE = StringToBitMap(sessionManager.getPreferences(MainActivity.this, "Image"));
        mAdapter = new MyAdapter(this, TITLES, ICONS, NAME, PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.open_drawer, R.string.close_drawer) {
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

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
}