package com.tech.tnqguru.studentactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.tech.tnqguru.R;
import com.tech.tnqguru.common.ColgStuFeesActivity;
import com.tech.tnqguru.common.LoginActivity;
import com.tech.tnqguru.common.ProfileActivity;
import com.tech.tnqguru.common.ScholFeesStandardSelectionActivity;
import com.tech.tnqguru.facultyactivity.FacultyDrawerActivity;
import com.tech.tnqguru.utils.PreferenceUtil;

public class StudentDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_drawer);

        Toolbar toolbar = findViewById(R.id.toolbarStudent);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout_student);
        NavigationView navigationView = findViewById(R.id.nav_view_student);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
                intent = new Intent(StudentDrawerActivity.this, ScholFeesStandardSelectionActivity.class);
                break;

            case R.id.nav_chat:
                intent = new Intent(StudentDrawerActivity.this, ColgStuFeesActivity.class);
                break;

            case R.id.nav_profile:
                intent = new Intent(StudentDrawerActivity.this, ProfileActivity.class);
                break;

            case R.id.nav_logout:
                clearAllData();
                break;
        }

        startActivity(intent);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void clearAllData() {

        PreferenceUtil.clear(StudentDrawerActivity.this);
        Intent intent=new Intent(StudentDrawerActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}