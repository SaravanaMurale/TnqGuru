package com.tech.tnqguru.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.ColgStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.ScholStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.UserDetailsForPayemntDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;
import com.tech.tnqguru.utils.LoaderUtil;
import com.tech.tnqguru.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScholStuFeesActivity extends AppCompatActivity implements ScholStuFeesAdapter.ScholFeesClickListener {


    RecyclerView scholStuFeesRecyclerView;
    ScholStuFeesAdapter scholStuFeesAdapter;
    List<ScholStuFeesResponseDTO> scholStuFeesResponseDTOList;

    Button btnScholFeesSubmit;
    String selectedStd;

    String userName,userMobile,userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schol_stu_fees);

        Intent intent=getIntent();
        selectedStd=intent.getStringExtra("SELECTED_STD");

        String[] words=selectedStd.split("/");

        String std=words[0];
        String board=words[1];

        scholStuFeesRecyclerView = (RecyclerView) findViewById(R.id.scholStuFeesRecyclerView);
        scholStuFeesRecyclerView.setHasFixedSize(true);
        scholStuFeesRecyclerView.setLayoutManager(new LinearLayoutManager(ScholStuFeesActivity.this));
        scholStuFeesResponseDTOList = new ArrayList<>();


        btnScholFeesSubmit = (Button) findViewById(R.id.btnScholFeesSubmit);

        scholStuFeesAdapter=new ScholStuFeesAdapter(ScholStuFeesActivity.this,scholStuFeesResponseDTOList,this);

        scholStuFeesRecyclerView.setAdapter(scholStuFeesAdapter);

        getScholStuFeesDetails(std,board);
        
    }

    private void getScholStuFeesDetails(String std, String board) {

        Dialog dialog= LoaderUtil.showProgressBar(this);

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<List<ScholStuFeesResponseDTO>> call = apiInterface.getScholStuFeesDetails(std,board);
        
        call.enqueue(new Callback<List<ScholStuFeesResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ScholStuFeesResponseDTO>> call, Response<List<ScholStuFeesResponseDTO>> response) {

                scholStuFeesResponseDTOList=response.body();
                scholStuFeesAdapter.setData(scholStuFeesResponseDTOList);

                LoaderUtil.dismisProgressBar(ScholStuFeesActivity.this,dialog);
            }

            @Override
            public void onFailure(Call<List<ScholStuFeesResponseDTO>> call, Throwable t) {
                LoaderUtil.dismisProgressBar(ScholStuFeesActivity.this,dialog);
            }
        });
        
    }
    

    @Override
    public void scholFeesClick(String modeOfFees) {
        btnScholFeesSubmit.setVisibility(View.VISIBLE);
        btnScholFeesSubmit.setText("PAY  "+"₹"+modeOfFees);

        btnScholFeesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserDetails(modeOfFees);

            }
        });

    }

    private void getUserDetails(String modeOfFees) {

        Dialog dialog= LoaderUtil.showProgressBar(this);

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        int userId=Integer.parseInt( PreferenceUtil.getValueString(ScholStuFeesActivity.this,PreferenceUtil.USER_ID));

        Call<UserDetailsForPayemntDTO> call=apiInterface.getDetailsForScholStudent(userId);

        call.enqueue(new Callback<UserDetailsForPayemntDTO>() {
            @Override
            public void onResponse(Call<UserDetailsForPayemntDTO> call, Response<UserDetailsForPayemntDTO> response) {


                UserDetailsForPayemntDTO userDetailsForPayemntDTO=response.body();
                userName=userDetailsForPayemntDTO.getUserName();
                userMobile=userDetailsForPayemntDTO.getUserMobile();
                userEmail=userDetailsForPayemntDTO.getUserEmail();

                LoaderUtil.dismisProgressBar(ScholStuFeesActivity.this,dialog);

                callPaymentGatewayActivity(modeOfFees);

            }

            @Override
            public void onFailure(Call<UserDetailsForPayemntDTO> call, Throwable t) {

                LoaderUtil.dismisProgressBar(ScholStuFeesActivity.this,dialog);

            }
        });

    }

    private void callPaymentGatewayActivity(String modeOfFees) {


        int courseFees=Integer.parseInt(modeOfFees);

        Intent intent = new Intent(ScholStuFeesActivity.this, PaymentGatewayActivity.class);
        intent.putExtra("USERNAME", userName);
        intent.putExtra("MOBILENUMBER", userMobile);
        intent.putExtra("AMOUNT", courseFees);
        intent.putExtra("EMAIL", userEmail);
        intent.putExtra("COURSE","Schol Android Course");
        startActivity(intent);

    }
}