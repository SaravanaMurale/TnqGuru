package com.tech.tnqguru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    //Image Upload To Server
    //https://www.youtube.com/watch?v=5fTN9INBGR8


    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.conferenceName);
        btn = (Button) findViewById(R.id.btn);

       /* try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setWelcomePageEnabled(false)
                    .setFeatureFlag("invite.enabled", 0)

                    .build();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/


        JitsiMeetConferenceOptions options
                = new JitsiMeetConferenceOptions.Builder()
                .setRoom("Murali")
                .setFeatureFlag("meeting-password.enabled",true)
                .setFeatureFlag("invite.enabled", true)
                .build();
        JitsiMeetActivity.launch(MainActivity.this, options);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = editText.getText().toString();
                if (text.length() > 0) {
                    JitsiMeetConferenceOptions options
                            = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(text)
                            .setFeatureFlag("meeting-password.enabled",false)
                            .setFeatureFlag("invite.enabled", true)
                            .build();
                    JitsiMeetActivity.launch(MainActivity.this, options);

                }


            }


        });*/


    }
}