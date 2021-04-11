package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;

public class SchoolStuRegFragment  extends Fragment {


    Spinner spinnerScholStuReg,spinnerScholStuModeOfClass,spinnerScholStuCountry;
    String spnSelectScholStu,spnScholStuModeOfClass;
    Button btnScholStuReg;

    EditText scholStuNameEdit,scholStuMobileEdit,scholStuAddressEdit,scholStuPincodeEdit,scholStuEmailEdit,scholStuIdProofNumberEdit,scholStuPasswordEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_school_stu_reg,container,false);

        initView(view);

        btnScholStuReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllEnteredScholStuDetails();
            }
        });

        return view;
    }

    private void getAllEnteredScholStuDetails() {

        String scholStuName,scholStuMobile,scholStuAddress,scholStuPincode,scholStuEmail,scholStuPassword,scholStuAbout,scholFacIdProofNumber;

        scholStuName=scholStuNameEdit.getText().toString();
        scholStuMobile=scholStuMobileEdit.getText().toString();
        scholStuAddress=scholStuAddressEdit.getText().toString();
        scholStuPincode=scholStuPincodeEdit.getText().toString();
        scholStuEmail=scholStuEmailEdit.getText().toString();
        scholStuPassword=scholStuPasswordEdit.getText().toString();

        doRegisterCollegeStudent(scholStuName,scholStuMobile,scholStuAddress,scholStuPincode,scholStuEmail,scholStuPassword);

    }

    private void doRegisterCollegeStudent(String scholStuName, String scholStuMobile, String scholStuAddress, String scholStuPincode, String scholStuEmail, String scholStuPassword) {



    }

    private void initView(View view) {

        setView(view);

    }

    private void setView(View view) {

        spinnerScholStuReg = (Spinner) view.findViewById(R.id.spinnerSelectSchool);
        spinnerScholStuModeOfClass = (Spinner) view.findViewById(R.id.spinnerScholStuCountry);
        spinnerScholStuCountry = (Spinner) view.findViewById(R.id.modeOfScholStuClass);

        scholStuNameEdit=(EditText)view.findViewById(R.id.scholStuName);
        scholStuMobileEdit=(EditText)view.findViewById(R.id.scholStuMobile);
        scholStuAddressEdit=(EditText)view.findViewById(R.id.scholStuAddress);
        scholStuPincodeEdit=(EditText)view.findViewById(R.id.scholStuPincode);
        scholStuEmailEdit=(EditText)view.findViewById(R.id.scholStuEmail);
        scholStuPasswordEdit= (EditText)view.findViewById(R.id.scholStuPassword);
        scholStuIdProofNumberEdit=(EditText)view.findViewById(R.id.colgStuPassword);

    }
}
