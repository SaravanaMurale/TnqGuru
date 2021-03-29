package com.tech.tnqguru.common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.tech.tnqguru.R;

import org.json.JSONObject;

public class PaymentGatewayActivity extends AppCompatActivity implements PaymentResultListener {

    TextView courseAmount,userSelectedCourse;
    String userName, userMobileNumber, userEmail, selectedCourse;
    int amount, amountInPaise;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        Checkout.preload(getApplicationContext());

        Intent intent = getIntent();
        userName = intent.getStringExtra("USERNAME");
        userMobileNumber = intent.getStringExtra("MOBILENUMBER");
        userEmail = intent.getStringExtra("EMAIL");
        amount = intent.getIntExtra("AMOUNT", 0);
        selectedCourse = intent.getStringExtra("COURSE");

        if (amount > 0) {
            amountInPaise = amount * 100;
        } else {
            return;
        }


        courseAmount = (TextView) findViewById(R.id.courseAmount);
        userSelectedCourse=(TextView)findViewById(R.id.userSelectedCourse);
        btnPay = (Button) findViewById(R.id.btnPay);
        courseAmount.setText("₹" + amount);
        btnPay.setText("PAY ₹" + amount);
        userSelectedCourse.setText(selectedCourse);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });


    }

    public void startPayment() {

        Checkout checkout = new Checkout();  /**   * Set your logo here   */
        checkout.setKeyID("rzp_test_EFYJf29T1UAjUx");    /**   * Instantiate Checkout   */

        //checkout.setImage(R.drawable.logo);  /**   * Reference to current activity   */
        final Activity activity = this;  /**   * Pass your payment options to the Razorpay Checkout as a JSONObject   */
        try {
            JSONObject options = new JSONObject();

            options.put("name", userName);
            options.put("description", selectedCourse);
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order#_firest_@123");//from response of step 3.
            options.put("currency", "INR");
            options.put("amount", amountInPaise);  //pass amount in currency subunits
            options.put("prefill.contact", userMobileNumber);
            checkout.open(activity, options);

              options.put("theme.color", "#43DFE1");
            options.put("prefill.email", userEmail);


        } catch (Exception e) {
            System.out.println("ErrorPayment" + e.getMessage().toString());
            //Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        System.out.println("PaymentGatewaySuccess" + s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        System.out.println("PaymentGatewaySuccess" + s);

    }
}