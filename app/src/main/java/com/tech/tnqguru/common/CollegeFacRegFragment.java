package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;

public class CollegeFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner spinnerSelectColg,spinnerColgFacCountry,spinnerColgFacDept,spinnerColgTeachExp,spinnerColgIndusExp,modeOfColgClass;

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


    }

    private void setView(View view) {

        spinnerSelectColg = (Spinner) view.findViewById(R.id.spinnerSelectColg);
        spinnerColgFacCountry = (Spinner) view.findViewById(R.id.spinnerColgFacCountry);
        spinnerColgFacDept=(Spinner)view.findViewById(R.id.spinnerColgFacDept);
        spinnerColgTeachExp=(Spinner)view.findViewById(R.id.spinnerColgTeachExp);
        spinnerColgIndusExp=(Spinner)view.findViewById(R.id.spinnerColgIndusExp);
        modeOfColgClass=(Spinner)view.findViewById(R.id.modeOfColgClass);




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
