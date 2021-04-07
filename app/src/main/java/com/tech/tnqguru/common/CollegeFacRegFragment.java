package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollegeFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner spinnerSelectColg,spinnerColgFacCountry,spinnerColgFacDept,spinnerColgTeachExp,spinnerColgIndusExp,modeOfColgClass;
    Button btnColFacReg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.layout_college_fac_registration,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        setView(view);

        ArrayAdapter<CharSequence> selectColg=ArrayAdapter.createFromResource(getActivity(),R.array.colg_type,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectColg.setAdapter(selectColg);
        spinnerSelectColg.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> countryAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.country,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgFacCountry.setAdapter(countryAdapter);
        spinnerColgFacCountry.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> colgDeptAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.colg_dept,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgFacDept.setAdapter(colgDeptAdapter);
        spinnerColgFacDept.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> teachExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.total_exp,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgTeachExp.setAdapter(teachExpAdapter);
        spinnerColgTeachExp.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> indusExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.indus_exp,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgIndusExp.setAdapter(indusExpAdapter);
        spinnerColgIndusExp.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> modeOfClassExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeOfColgClass.setAdapter(modeOfClassExpAdapter);
        modeOfColgClass.setOnItemSelectedListener(this);

        btnColFacReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doRegisterCollegeFaculty();

            }
        });


    }

    private void doRegisterCollegeFaculty() {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<BaseResponseDTO> call=apiInterface.doCollegeFacRegistration(
                "Murali",
                "murali@gmail.com",
                "9999999999",
                "photo.jpg",
                "India",
                "address",
                "600055",
                "B.Tech",
                "5",
                "online",
                "bioData",
                "Fac Subject",
                "5",
                "About Factulty",
                "B.Tech",
                "Computer Science",
                "Faculty Id Proof",
                "1111111111",
                "Fac Bank Details",
                "murali@gmail.com",
                "aaaaaaaaaa");

        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

                BaseResponseDTO baseResponseDTO=response.body();

                System.out.println("RegistrationResponse"+baseResponseDTO.getResponseMessage()+" "+baseResponseDTO.getResponseCode());


            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {

            }
        });



    }

    private void setView(View view) {

        spinnerSelectColg = (Spinner) view.findViewById(R.id.spinnerSelectColg);
        spinnerColgFacCountry = (Spinner) view.findViewById(R.id.spinnerColgFacCountry);
        spinnerColgFacDept=(Spinner)view.findViewById(R.id.spinnerColgFacDept);
        spinnerColgTeachExp=(Spinner)view.findViewById(R.id.spinnerColgTeachExp);
        spinnerColgIndusExp=(Spinner)view.findViewById(R.id.spinnerColgIndusExp);
        modeOfColgClass=(Spinner)view.findViewById(R.id.modeOfColgClass);

        btnColFacReg=(Button)view.findViewById(R.id.btnColFacReg);




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
