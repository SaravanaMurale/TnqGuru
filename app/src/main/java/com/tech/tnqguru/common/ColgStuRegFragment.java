package com.tech.tnqguru.common;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;
import com.tech.tnqguru.spinneradapter.SpinAdapter;
import com.tech.tnqguru.spinneradapter.SpinMaxSubAdapter;
import com.tech.tnqguru.utils.AppConstant;
import com.tech.tnqguru.utils.LoaderUtil;
import com.tech.tnqguru.utils.Validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColgStuRegFragment extends Fragment implements AdapterView.OnItemSelectedListener, SpinMaxSubAdapter.SpinnerMaxSubCheckBoxSelectedListener {

    Spinner spinnerColgStuReg,spinnerColgStuModeOfClass,spinnerColgStuDept,spinnerColgCourseName;
    String spnSelectColgStu,spnColgStuModeOfClass,spinColgStuDept;
    Button colgStuUploadImage,btnColStuReg;

    EditText colgStuNameEdit,colgStuMobileEdit,colgStuAddressEdit,colgStuPincodeEdit,colgStuEmailEdit,colgStuIdProofNumberEdit,colgStuPasswordEdit,colgStuAboutEdit;

    private List<String> CourseName;

    private RelativeLayout colgDOBBlock;
    private TextView colgStuDOB;
    DatePickerDialog.OnDateSetListener setListenerDateOfBirth;
    String colgStuDOBInString;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_colg_stu_reg,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        CourseName =new ArrayList<>();

        setView(view);

        ArrayAdapter<CharSequence> indusExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.colg_type,android.R.layout.simple_spinner_item);
        indusExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgStuReg.setAdapter(indusExpAdapter);
        spinnerColgStuReg.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> modeOfClassExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        modeOfClassExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgStuModeOfClass.setAdapter(modeOfClassExpAdapter);
        spinnerColgStuModeOfClass.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> countryAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.colg_dept,android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgStuDept.setAdapter(countryAdapter);
        spinnerColgStuDept.setOnItemSelectedListener(this);


        List<SpinAdapter> listVOs=new ArrayList<>();

        List<String> getColgMaxSub= AppConstant.getColgMaxSubject();

        for (int i = 0; i < getColgMaxSub.size(); i++) {
            SpinAdapter stateVO = new SpinAdapter();
            stateVO.setTitle(getColgMaxSub.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        SpinMaxSubAdapter spinMaxSubAdapter = new SpinMaxSubAdapter(getActivity(), 0, listVOs, ColgStuRegFragment.this);
        spinnerColgCourseName.setAdapter(spinMaxSubAdapter);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);


        colgDOBBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog, setListenerDateOfBirth, year, month, date);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListenerDateOfBirth = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                colgStuDOB.setText(date);

                colgStuDOBInString=date;

                System.out.println("selectedFromDate " + date);


            }
        };


        btnColStuReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllColgStuEnteredDetails();
            }
        });


    }

    private void getAllColgStuEnteredDetails() {

        String colgStuName,colgStuMobile,colgStuAddress,colgStuPincode,colgStuEmail,colgStuPassword,colgStuAbout,colgFacIdProofNumber;

        colgStuName=colgStuNameEdit.getText().toString();
        colgStuMobile=colgStuMobileEdit.getText().toString();
        colgStuAddress=colgStuAddressEdit.getText().toString();
        colgStuPincode=colgStuPincodeEdit.getText().toString();
        colgStuEmail=colgStuEmailEdit.getText().toString();
        colgStuPassword=colgStuPasswordEdit.getText().toString();
        colgStuAbout=colgStuAboutEdit.getText().toString();
        colgFacIdProofNumber=colgStuIdProofNumberEdit.getText().toString();

        colgStudentValidation(colgStuName,colgStuMobile,colgStuAddress,colgStuPincode,colgStuEmail,colgStuPassword,colgStuAbout,colgFacIdProofNumber);
        



    }

    private void colgStudentValidation(String colgStuName, String colgStuMobile, String colgStuAddress, String colgStuPincode, String colgStuEmail, String colgStuPassword, String colgStuAbout, String colgFacIdProofNumber) {

        if(Validation.nullValidation(spnSelectColgStu)){
            Toast.makeText(getActivity(), "Please Select College", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(colgStuName)){
            Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_LONG).show();
            return;
        }
        if(Validation.nullValidation(colgStuMobile)){
            Toast.makeText(getActivity(), "Please Enter Mobile", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(colgStuDOBInString)){
            Toast.makeText(getActivity(), "Please Select DOB", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(spinColgStuDept)){
            Toast.makeText(getActivity(), "Please Department", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(colgStuAddress)){
            Toast.makeText(getActivity(), "Please Enter Address", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(colgStuPincode)){
            Toast.makeText(getActivity(), "Please Enter Pincode", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(colgStuEmail)){
            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_LONG).show();
            return;
        }

        if(Validation.nullValidation(colgStuPassword)){
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }
        if(Validation.nullValidation(spnColgStuModeOfClass)){
            Toast.makeText(getActivity(), "Please Select Mode Of Class", Toast.LENGTH_LONG).show();
            return;
        }

        //Photo Validation

        doRegisterCollegeStudent(colgStuName,colgStuMobile,colgStuAddress,colgStuPincode,colgStuEmail,colgStuPassword);
    }


    private void doRegisterCollegeStudent(String colgStuName, String colgStuMobile, String colgStuAddress, String colgStuPincode, String colgStuEmail, String colgStuPassword) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<BaseResponseDTO> call=apiInterface.doCollegeStudentRegistration(
                spnSelectColgStu,
                colgStuName,
                colgStuMobile,
                colgStuDOBInString,
                spinColgStuDept,

                colgStuAddress,
                colgStuPincode,
                colgStuEmail,
                spnColgStuModeOfClass,
                CourseName,
                "Photo",
                "SyllabusDocument",
                colgStuEmail,
                colgStuPassword
        );

        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {

            }
        });


    }

    private void setView(View view) {

        spinnerColgStuReg = (Spinner) view.findViewById(R.id.spinnerSelectColStu);
        spinnerColgStuModeOfClass = (Spinner) view.findViewById(R.id.colgStuModeOfClass);
        spinnerColgStuDept = (Spinner) view.findViewById(R.id.spinnerColgStuDept);
        spinnerColgCourseName=(Spinner)view.findViewById(R.id.colgStuCourseName);

        colgDOBBlock=(RelativeLayout)view.findViewById(R.id.colgStuDOBBlock);
        colgStuDOB=(TextView)view.findViewById(R.id.colgStuDOB);


        colgStuNameEdit=(EditText)view.findViewById(R.id.colgStuName);
        colgStuMobileEdit=(EditText)view.findViewById(R.id.colgStuMobile);
        colgStuAddressEdit=(EditText)view.findViewById(R.id.colgStuAddress);
        colgStuAboutEdit=(EditText)view.findViewById(R.id.colgStuAbout);
        colgStuPincodeEdit=(EditText)view.findViewById(R.id.colgStuPincodeInput);
        colgStuEmailEdit=(EditText)view.findViewById(R.id.colgStuEmail);
        colgStuPasswordEdit= (EditText)view.findViewById(R.id.colgStuPassword);
        colgStuIdProofNumberEdit=(EditText)view.findViewById(R.id.colgStuPassword);

        colgStuUploadImage=(Button)view.findViewById(R.id.colgStuUploadImage);
        btnColStuReg=(Button)view.findViewById(R.id.btnColStuReg);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

         if(adapterView.getId() == R.id.spinnerSelectColStu){
             spnSelectColgStu = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacIndusExp " + spnSelectColgStu);
        }

        else if (adapterView.getId() == R.id.colgStuModeOfClass) {
             spnColgStuModeOfClass = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacModeOfClass " + spnColgStuModeOfClass);
        }else if (adapterView.getId() == R.id.spinnerColgStuDept) {
             spinColgStuDept = adapterView.getItemAtPosition(i).toString();
             System.out.println("ColgFacModeOfClass " + spnColgStuModeOfClass);
         }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void selectMaxSpinnerCheckBox(String item, boolean status) {
        if(status){
            CourseName.add(item);
        }else {
            CourseName.remove(item);
        }
    }
}
