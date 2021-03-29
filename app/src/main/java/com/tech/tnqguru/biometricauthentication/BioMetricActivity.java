package com.tech.tnqguru.biometricauthentication;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.tech.tnqguru.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BioMetricActivity extends AppCompatActivity {

    TextView authStatusTv;
    Button authBtn;

    Executor executor;
    //BiometricPrompt biometricPrompt;
    //BiometricPromptrompt.PromptInfo promptInfo;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_metric);

        authStatusTv = (TextView) findViewById(R.id.authStatusTv);
        authBtn = (Button) findViewById(R.id.authBtn);

        executor = Executors.newSingleThreadExecutor();

        final BioMetricActivity bioMetricActivity = this;

        final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("FingerPrint Authentication")
                .setSubtitle("SubTitle")
                .setDescription("Description")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).build();


        authBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);

                        bioMetricActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(BioMetricActivity.this, "Authenticated", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                });

            }
        });


    }
}