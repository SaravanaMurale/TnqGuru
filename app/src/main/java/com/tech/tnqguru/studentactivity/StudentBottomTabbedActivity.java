package com.tech.tnqguru.studentactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tech.tnqguru.R;
import com.tech.tnqguru.facultyfragment.FacultyAccountFragment;
import com.tech.tnqguru.facultyfragment.FacultyHomeFragment;
import com.tech.tnqguru.facultyfragment.FacultySearchFragment;
import com.tech.tnqguru.facultyfragment.FacultyWishlistFragment;
import com.tech.tnqguru.studentfragment.StudentAccountFragment;
import com.tech.tnqguru.studentfragment.StudentHomeFragment;
import com.tech.tnqguru.studentfragment.StudentSearchFragment;
import com.tech.tnqguru.studentfragment.StudentWishlistFragment;

public class StudentBottomTabbedActivity extends AppCompatActivity {

    private BottomNavigationView studentBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_bottom_tabbed);


        studentBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomTabbedViewStudent);
        studentBottomNavigationView.setOnNavigationItemSelectedListener(studentNavListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_student, new StudentHomeFragment()).commit();


    }

    BottomNavigationView.OnNavigationItemSelectedListener studentNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedStudentFragment = null;

            switch (menuItem.getItemId()) {

                case R.id.menu_home:
                    selectedStudentFragment = new StudentHomeFragment();
                    break;

                case R.id.menu_search:
                    selectedStudentFragment = new StudentSearchFragment();
                    break;

                case R.id.menu_wishlist:


                    selectedStudentFragment = new StudentWishlistFragment();
                    break;

                case R.id.menu_account:
                    selectedStudentFragment = new StudentAccountFragment();
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_student, selectedStudentFragment).commit();

            return true;
        }


    };
}