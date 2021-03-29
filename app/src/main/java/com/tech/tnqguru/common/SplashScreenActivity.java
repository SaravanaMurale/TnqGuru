package com.tech.tnqguru.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tech.tnqguru.R;
import com.tech.tnqguru.facultyactivity.FacultyBottomTabbedActivity;
import com.tech.tnqguru.utils.ToastUtils;

public class SplashScreenActivity extends AppCompatActivity {

    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userId = "user1";


        new SplashDownCountDown(3000, 1000).start();


    }

    private class SplashDownCountDown extends CountDownTimer {

        SplashDownCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);


        }

        @Override
        public void onTick(long milliSecond) {

        }

        @Override
        public void onFinish() {


            if (userId != null) {

                Intent intent = getIntent();
                String action = intent.getAction();
                Uri data = intent.getData();

                if (data != null) {
                    System.out.println("URLDATA " + data.getPath());
                    System.out.println("URLDATA " + data);
                    System.out.println("ACTION " + action);

                    Intent callJitsiActivity = new Intent(SplashScreenActivity.this, JitsiActivity.class);
                    callJitsiActivity.putExtra("ROOM", data.getPath());
                    startActivity(callJitsiActivity);

                } else {
                    Intent callJitsiActivity = new Intent(SplashScreenActivity.this, FacultyBottomTabbedActivity.class);
                    startActivity(callJitsiActivity);
                }


            } else if (userId == null) {

                ToastUtils.getInstance(SplashScreenActivity.this).showShortToast(getString(R.string.pls_login));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent callJitsiActivity = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(callJitsiActivity);
                    }
                }, 2000);


            }


        }
    }


}