package com.tech.tnqguru.facultyactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tech.tnqguru.R;
import com.tech.tnqguru.facultyfragment.FacultyAccountFragment;
import com.tech.tnqguru.facultyfragment.FacultyHomeFragment;
import com.tech.tnqguru.facultyfragment.FacultySearchFragment;
import com.tech.tnqguru.facultyfragment.FacultyWishlistFragment;

public class FacultyBottomTabbedActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabbed);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomTabbedView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FacultyHomeFragment()).commit();


    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {

                case R.id.menu_home:
                    selectedFragment = new FacultyHomeFragment();
                    break;

                case R.id.menu_search:
                    selectedFragment = new FacultySearchFragment();
                    break;

                case R.id.menu_wishlist:


                    selectedFragment = new FacultyWishlistFragment();
                    break;

                case R.id.menu_account:
                    selectedFragment = new FacultyAccountFragment();
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }


    };
}