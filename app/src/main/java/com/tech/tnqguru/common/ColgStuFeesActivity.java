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
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
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

public class ColgStuFeesActivity extends AppCompatActivity implements ColgStuFeesAdapter.ColgFeesClickListener {

    RecyclerView colgStuFeesRecyclerView;
    ColgStuFeesAdapter colgStuFeesAdapter;
    List<ColgStuFeesResponseDTO> colgStuFeesResponseDTOList;

    Button btnColgFeesSubmit;

    String userName,userMobile,userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colg_stu_fees);

        colgStuFeesRecyclerView = (RecyclerView) findViewById(R.id.colgStuFeesRecyclerView);
        colgStuFeesRecyclerView.setHasFixedSize(true);
        colgStuFeesRecyclerView.setLayoutManager(new LinearLayoutManager(ColgStuFeesActivity.this));
        colgStuFeesResponseDTOList = new ArrayList<>();

        btnColgFeesSubmit = (Button) findViewById(R.id.btnColgFeesSubmit);

        colgStuFeesAdapter = new ColgStuFeesAdapter(ColgStuFeesActivity.this, colgStuFeesResponseDTOList, this);

        colgStuFeesRecyclerView.setAdapter(colgStuFeesAdapter);



        getColgStuFeesDetails();
    }

    private void getColgStuFeesDetails() {


        Dialog dialog= LoaderUtil.showProgressBar(this);

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<List<ColgStuFeesResponseDTO>> call = apiInterface.getColgStuFeesDetails();

        call.enqueue(new Callback<List<ColgStuFeesResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ColgStuFeesResponseDTO>> call, Response<List<ColgStuFeesResponseDTO>> response) {

                colgStuFeesResponseDTOList = response.body();

                colgStuFeesAdapter.setData(colgStuFeesResponseDTOList);

                LoaderUtil.dismisProgressBar(ColgStuFeesActivity.this,dialog);

            }

            @Override
            public void onFailure(Call<List<ColgStuFeesResponseDTO>> call, Throwable t) {
                LoaderUtil.dismisProgressBar(ColgStuFeesActivity.this,dialog);
            }
        });

    }

    @Override
    public void colgFeesClick(String modeOfFees) {

        btnColgFeesSubmit.setVisibility(View.VISIBLE);
        btnColgFeesSubmit.setText("PAY  "+"???"+modeOfFees);

        btnColgFeesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserDetails(modeOfFees);



            }
        });

    }

    private void getUserDetails(String modeOfFees) {

        Dialog dialog= LoaderUtil.showProgressBar(this);

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        int userId=Integer.parseInt(PreferenceUtil.getValueString(ColgStuFeesActivity.this,PreferenceUtil.USER_ID));

        Call<UserDetailsForPayemntDTO> call=apiInterface.getDetailsForColgStudent(userId);

        call.enqueue(new Callback<UserDetailsForPayemntDTO>() {
            @Override
            public void onResponse(Call<UserDetailsForPayemntDTO> call, Response<UserDetailsForPayemntDTO> response) {


                UserDetailsForPayemntDTO userDetailsForPayemntDTO=response.body();
                userName=userDetailsForPayemntDTO.getUserName();
                userMobile=userDetailsForPayemntDTO.getUserMobile();
                userEmail=userDetailsForPayemntDTO.getUserEmail();

                LoaderUtil.dismisProgressBar(ColgStuFeesActivity.this,dialog);

                callPaymentGatewayActivity(modeOfFees);
            }

            @Override
            public void onFailure(Call<UserDetailsForPayemntDTO> call, Throwable t) {
                LoaderUtil.dismisProgressBar(ColgStuFeesActivity.this,dialog);
            }
        });


    }

    private void callPaymentGatewayActivity(String modeOfFees) {

        int courseFees=Integer.parseInt(modeOfFees);

        Intent intent = new Intent(ColgStuFeesActivity.this, PaymentGatewayActivity.class);
        intent.putExtra("USERNAME", userName);
        intent.putExtra("MOBILENUMBER", userMobile);
        intent.putExtra("AMOUNT", courseFees);
        intent.putExtra("EMAIL", userEmail);
        intent.putExtra("COURSE","Colg Android Course");
        startActivity(intent);



    }


}