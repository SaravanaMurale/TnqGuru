package com.tech.tnqguru.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tech.tnqguru.R;
import com.tech.tnqguru.facultyactivity.FacultyBottomTabbedActivity;
import com.tech.tnqguru.studentactivity.StudentBottomTabbedActivity;
import com.tech.tnqguru.utils.PreferenceUtil;
import com.tech.tnqguru.utils.ToastUtils;

public class SplashScreenActivity extends AppCompatActivity {

    String userId;
    String privilege;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userId = PreferenceUtil.getValueString(SplashScreenActivity.this,PreferenceUtil.USER_ID);
        privilege = PreferenceUtil.getValueString(SplashScreenActivity.this,PreferenceUtil.PRIVILEGE);


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
                    finish();


                } else if(data==null) {

                    if(privilege.equals("3") || privilege.equals("4")){
                        Intent callJitsiActivity = new Intent(SplashScreenActivity.this, StudentBottomTabbedActivity.class);
                        startActivity(callJitsiActivity);
                        finish();
                    }else if(privilege.equals("1") || privilege.equals("2")){
                        Intent callJitsiActivity = new Intent(SplashScreenActivity.this, FacultyBottomTabbedActivity.class);
                        startActivity(callJitsiActivity);
                        finish();
                    }


                }


            } else if (userId == null) {

                ToastUtils.getInstance(SplashScreenActivity.this).showShortToast(getString(R.string.pls_login));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent callJitsiActivity = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(callJitsiActivity);
                        finish();
                    }
                }, 2000);


            }


        }
    }


}