package com.tech.tnqguru.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.ColgStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;

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

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<List<ColgStuFeesResponseDTO>> call = apiInterface.getColgStuFeesDetails();

        call.enqueue(new Callback<List<ColgStuFeesResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ColgStuFeesResponseDTO>> call, Response<List<ColgStuFeesResponseDTO>> response) {

                colgStuFeesResponseDTOList = response.body();

                colgStuFeesAdapter.setData(colgStuFeesResponseDTOList);


            }

            @Override
            public void onFailure(Call<List<ColgStuFeesResponseDTO>> call, Throwable t) {

            }
        });

    }

    @Override
    public void colgFeesClick(String modeOfFees) {

        btnColgFeesSubmit.setVisibility(View.VISIBLE);
        btnColgFeesSubmit.setText(modeOfFees);

        btnColgFeesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // callPaymentGatewayActivity();

            }
        });

    }


}