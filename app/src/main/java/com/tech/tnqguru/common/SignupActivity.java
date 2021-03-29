package com.tech.tnqguru.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.tnqguru.R;

import static com.tech.tnqguru.utils.MathUtil.validateEmail;
import static com.tech.tnqguru.utils.MathUtil.validateMobile;
import static com.tech.tnqguru.utils.MathUtil.validateName;
import static com.tech.tnqguru.utils.MathUtil.validatePassword;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText signupUserName, signupMobile, signUpEmail, signUpPassword;
    Button btnSignup;
    Spinner courseSelectionSpinner;
    String spinnerSelectedCourse;
    String[] courses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);



        courseSelectionSpinner = (Spinner) findViewById(R.id.signUpCourse);
        signupUserName = (EditText) findViewById(R.id.signUpUserName);
        signupMobile = (EditText) findViewById(R.id.signUpMobile);
        signUpEmail = (EditText) findViewById(R.id.signUpEmail);
        signUpPassword = (EditText) findViewById(R.id.signUpPassword);

        btnSignup = (Button) findViewById(R.id.btnSignUp);


        courses = getResources().getStringArray(R.array.courses);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courses);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        courseSelectionSpinner.setAdapter(dataAdapter);
        courseSelectionSpinner.setOnItemSelectedListener(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String userName = signupUserName.getText().toString();
                String userEmail = signUpEmail.getText().toString();
                String userMobile = signupMobile.getText().toString();
                String userPassword = signUpPassword.getText().toString();

                if (userName.isEmpty() || userName.equals("") || userName.equals(null)) {
                    Toast.makeText(SignupActivity.this, "Please enter valid Name", Toast.LENGTH_LONG).show();
                    return;

                }

                if (userEmail.isEmpty() || userEmail.equals("") || userEmail.equals(null)) {
                    Toast.makeText(SignupActivity.this, "Please enter valid email", Toast.LENGTH_LONG).show();
                    return;

                }

                if (userMobile.isEmpty() || userMobile.equals("") || userMobile.equals(null)) {
                    Toast.makeText(SignupActivity.this, "Please enter valid mobile number", Toast.LENGTH_LONG).show();
                    return;

                }

                if (userPassword.isEmpty() || userPassword.equals("") || userPassword.equals(null)) {
                    Toast.makeText(SignupActivity.this, "Please enter valid password", Toast.LENGTH_LONG).show();
                    return;

                }
                if (spinnerSelectedCourse.isEmpty() || spinnerSelectedCourse.equals("") || spinnerSelectedCourse.equals(null) || spinnerSelectedCourse.equals("Select Courses")) {
                    Toast.makeText(SignupActivity.this, "Please select course", Toast.LENGTH_LONG).show();
                    return;
                }


                if (validateName(userName) && validateEmail(userEmail) && validateMobile(userMobile) && validatePassword(userPassword)) {


                    signupUserName.getText().clear();
                    signUpEmail.getText().clear();
                    signupMobile.getText().clear();
                    signUpPassword.getText().clear();
                    //signUpAddress.getText().clear();

                    Toast.makeText(SignupActivity.this, "SignUp Successfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SignupActivity.this, PaymentGatewayActivity.class);
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("MOBILENUMBER", userMobile);
                    intent.putExtra("AMOUNT", 101);
                    intent.putExtra("EMAIL", userEmail);
                    intent.putExtra("COURSE",spinnerSelectedCourse);
                    startActivity(intent);


                } else {
                    Toast.makeText(SignupActivity.this, "Please enter valid formet", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        spinnerSelectedCourse = courses[i];
        System.out.println("SelectedCourse" + spinnerSelectedCourse);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}