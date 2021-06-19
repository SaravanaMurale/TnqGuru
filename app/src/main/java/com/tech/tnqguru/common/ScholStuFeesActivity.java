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
import com.tech.tnqguru.modelresponse.ScholStuFeesResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schol_stu_fees);

        Intent intent=getIntent();
        selectedStd=intent.getStringExtra("SELECTED_STD");

        String[] words=selectedStd.split("/");

        String std=words[0];
        String board=words[1];

        scholStuFeesRecyclerView = (RecyclerView) findViewById(R.id.colgStuFeesRecyclerView);
        scholStuFeesRecyclerView.setHasFixedSize(true);
        scholStuFeesRecyclerView.setLayoutManager(new LinearLayoutManager(ScholStuFeesActivity.this));
        scholStuFeesResponseDTOList = new ArrayList<>();


        btnScholFeesSubmit = (Button) findViewById(R.id.btnScholFeesSubmit);

        scholStuFeesAdapter=new ScholStuFeesAdapter(ScholStuFeesActivity.this,scholStuFeesResponseDTOList,this);

        scholStuFeesRecyclerView.setAdapter(scholStuFeesAdapter);

        getScholStuFeesDetails(std,board);
        
    }

    private void getScholStuFeesDetails(String std, String board) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<List<ScholStuFeesResponseDTO>> call = apiInterface.getScholStuFeesDetails(std,board);
        
        call.enqueue(new Callback<List<ScholStuFeesResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ScholStuFeesResponseDTO>> call, Response<List<ScholStuFeesResponseDTO>> response) {

                scholStuFeesResponseDTOList=response.body();
                scholStuFeesAdapter.setData(scholStuFeesResponseDTOList);
            }

            @Override
            public void onFailure(Call<List<ScholStuFeesResponseDTO>> call, Throwable t) {

            }
        });
        
    }
    

    @Override
    public void scholFeesClick(String modeOfFees) {
        btnScholFeesSubmit.setVisibility(View.VISIBLE);
        btnScholFeesSubmit.setText(modeOfFees);

        btnScholFeesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPaymentGatewayActivity();
            }
        });

    }

    private void callPaymentGatewayActivity() {
    }
}