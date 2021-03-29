package com.tech.tnqguru.common;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shobhitpuri.custombuttons.GoogleSignInButton;
import com.tech.tnqguru.R;
import com.tech.tnqguru.facultyactivity.FacultyBottomTabbedActivity;
import com.tech.tnqguru.studentactivity.StudentBottomTabbedActivity;

import static com.tech.tnqguru.utils.AppConstant.RESOLVE_HINT;
import static com.tech.tnqguru.utils.MathUtil.validateEmail;
import static com.tech.tnqguru.utils.MathUtil.validatePassword;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInButton gmailSignup;
    GoogleApiClient mCredentialsApiClient;

    private EditText loginGmail, loginPassword;
    private Button btnSignIn;
    private TextView signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gmailSignup = (GoogleSignInButton) findViewById(R.id.gmailSignup);
        loginGmail = (EditText) findViewById(R.id.login_gmail);
        loginPassword = (EditText) findViewById(R.id.login_password);
        btnSignIn = (Button) findViewById(R.id.btn_Login);
        signUp=(TextView)findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


        mCredentialsApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        gmailSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestHint();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = loginGmail.getText().toString();
                String password = loginPassword.getText().toString();


                if (userName.equals("student@gmail.com") && password.equals("student")) {

                    Intent intent = new Intent(LoginActivity.this, StudentBottomTabbedActivity.class);
                    startActivity(intent);


                } else if (userName.equals("faculty@gmail.com") && password.equals("faculty")) {
                    Intent intent = new Intent(LoginActivity.this, FacultyBottomTabbedActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Entered Username or Password is wrong", Toast.LENGTH_LONG).show();
                }


            }
        });

    }


    private void requestHint() {

        mCredentialsApiClient.connect();

        HintRequest hintRequest = new HintRequest.Builder()
                //.setPhoneNumberIdentifierSupported(true)
                .setEmailAddressIdentifierSupported(true)
                .build();


        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
                mCredentialsApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);

                System.out.println("GmailUserID" + credential.getId());
                System.out.println("GmailUser" + credential.getName() + " " + credential.getPassword() + " " + credential.getId() + " " + credential.getIdTokens());

            }
        }


    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String gamil = loginGmail.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            btnSignIn.setEnabled(validateEmail(gamil) && validatePassword(password));

            if (btnSignIn.isEnabled()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //btnSignIn.setBackground(getDrawable(R.drawable.rectangle_shpae));
                }
            } else if (!btnSignIn.isEnabled()) {
                btnSignIn.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //btnSignIn.setBackground(getDrawable(R.color.btn_disable));
                }
            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}