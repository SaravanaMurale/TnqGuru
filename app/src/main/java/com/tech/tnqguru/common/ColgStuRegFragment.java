package com.tech.tnqguru.common;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.spinneradapter.SpinAdapter;
import com.tech.tnqguru.spinneradapter.SpinMaxSubAdapter;
import com.tech.tnqguru.utils.AppConstant;
import com.tech.tnqguru.utils.LoaderUtil;

import java.util.ArrayList;
import java.util.List;

public class ColgStuRegFragment extends Fragment implements AdapterView.OnItemSelectedListener, SpinMaxSubAdapter.SpinnerMaxSubCheckBoxSelectedListener {

    Spinner spinnerColgStuReg,spinnerColgStuModeOfClass,spinnerColgStuDept,spinnerColgCourseName;
    String spnSelectColgStu,spnColgStuModeOfClass,spinColgStuDept;
    Button colgStuUploadImage,btnColStuReg;

    EditText colgStuNameEdit,colgStuMobileEdit,colgStuAddressEdit,colgStuPincodeEdit,colgStuEmailEdit,colgStuIdProofNumberEdit,colgStuPasswordEdit;

    private List<String> CourseName;



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


        if (colgStuName.isEmpty() || colgStuName.equals("") || colgStuName.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_LONG).show();
            return;

        }

        if (colgStuMobile.isEmpty() || colgStuMobile.equals("") || colgStuMobile.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Mobile", Toast.LENGTH_LONG).show();
            return;

        }

        if (colgStuAddress.isEmpty() || colgStuAddress.equals("") || colgStuAddress.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Address", Toast.LENGTH_LONG).show();
            return;

        }

        if (colgStuPincode.isEmpty() || colgStuPincode.equals("") || colgStuPincode.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Pincode", Toast.LENGTH_LONG).show();
            return;

        }

        if (colgStuEmail.isEmpty() || colgStuEmail.equals("") || colgStuEmail.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_LONG).show();
            return;

        }

        if (colgStuPassword.isEmpty() || colgStuPassword.equals("") || colgStuPassword.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
            return;

        }

        
       // doRegisterCollegeStudent(colgStuName,colgStuMobile,colgStuAddress,colgStuPincode,colgStuEmail,colgStuPassword);

    }


    private void doRegisterCollegeStudent(String colgStuName, String colgStuMobile, String colgStuAddress, String colgStuPincode, String colgStuEmail, String colgStuPassword) {
    }

    private void setView(View view) {

        spinnerColgStuReg = (Spinner) view.findViewById(R.id.spinnerSelectColStu);
        spinnerColgStuModeOfClass = (Spinner) view.findViewById(R.id.colgStuModeOfClass);
        spinnerColgStuDept = (Spinner) view.findViewById(R.id.spinnerColgStuDept);
        spinnerColgCourseName=(Spinner)view.findViewById(R.id.colgStuCourseName);


        colgStuNameEdit=(EditText)view.findViewById(R.id.colgStuName);
        colgStuMobileEdit=(EditText)view.findViewById(R.id.colgStuMobile);
        colgStuAddressEdit=(EditText)view.findViewById(R.id.colgStuAddress);
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
