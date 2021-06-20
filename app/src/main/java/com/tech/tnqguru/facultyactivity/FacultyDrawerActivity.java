package com.tech.tnqguru.facultyactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.tech.tnqguru.R;
import com.tech.tnqguru.common.ColgStuFeesActivity;
import com.tech.tnqguru.common.LoginActivity;
import com.tech.tnqguru.common.ProfileActivity;
import com.tech.tnqguru.common.ScholFeesStandardSelectionActivity;
import com.tech.tnqguru.studentactivity.StudentDrawerActivity;
import com.tech.tnqguru.utils.PreferenceUtil;

public class FacultyDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView facName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        facName=(TextView)findViewById(R.id.facName);

        String name= PreferenceUtil.getValueString(FacultyDrawerActivity.this,PreferenceUtil.USER_NAME);
        facName.setText(name);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             drawer.closeDrawer(GravityCompat.START);
            }
        },1000);
        toggle.syncState();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        Intent intent = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_message:
                intent = new Intent(FacultyDrawerActivity.this, ScholFeesStandardSelectionActivity.class);
                break;

            case R.id.nav_chat:
                intent = new Intent(FacultyDrawerActivity.this, ColgStuFeesActivity.class);
                break;

            case R.id.nav_profile:
                intent = new Intent(FacultyDrawerActivity.this, ProfileActivity.class);
                break;

            case R.id.nav_logout:
                clearAllData();
                break;
        }

        if(intent!=null){
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void clearAllData() {

        PreferenceUtil.clear(FacultyDrawerActivity.this);
        Intent intent=new Intent(FacultyDrawerActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}