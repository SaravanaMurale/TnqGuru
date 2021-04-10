package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;

public class ColgStuRegFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerColgStuReg,spinnerColgStuModeOfClass,spinnerColgStuCountry;
    String spnSelectColgStu,spnColgStuModeOfClass;
    Button btnColStuReg;

    EditText colgStuNameEdit,colgStuMobileEdit,colgStuAddressEdit,colgStuPincodeEdit,colgStuEmailEdit,colgStuIdProofNumberEdit,colgStuPasswordEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_colg_stu_reg,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        setView(view);

        ArrayAdapter<CharSequence> indusExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.colg_type,android.R.layout.simple_spinner_item);
        indusExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgStuReg.setAdapter(indusExpAdapter);
        spinnerColgStuReg.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> modeOfClassExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        modeOfClassExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgStuModeOfClass.setAdapter(modeOfClassExpAdapter);
        spinnerColgStuModeOfClass.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> countryAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.country,android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgStuCountry.setAdapter(countryAdapter);
        spinnerColgStuCountry.setOnItemSelectedListener(this);


    }

    private void setView(View view) {

        spinnerColgStuReg = (Spinner) view.findViewById(R.id.spinnerSelectColStu);
        spinnerColgStuModeOfClass = (Spinner) view.findViewById(R.id.colgStuModeOfClass);
        spinnerColgStuCountry = (Spinner) view.findViewById(R.id.spinnerColgStuCountry);


        colgStuNameEdit=(EditText)view.findViewById(R.id.colgStuName);
        colgStuMobileEdit=(EditText)view.findViewById(R.id.colgStuMobile);
        colgStuAddressEdit=(EditText)view.findViewById(R.id.colgStuAddress);
        colgStuPincodeEdit=(EditText)view.findViewById(R.id.colgStuPincodeInput);
        colgStuEmailEdit=(EditText)view.findViewById(R.id.colgStuEmail);
        colgStuIdProofNumberEdit=(EditText)view.findViewById(R.id.colgStuPassword);
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
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
