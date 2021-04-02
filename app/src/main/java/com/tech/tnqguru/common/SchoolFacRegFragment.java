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

public class SchoolFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerSchoLevel, spinnerCountry,spinnerTotalExp,spinnerIndusExp,spinnerModeOfClass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_school_fac_registration, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        spinnerSchoLevel = (Spinner) view.findViewById(R.id.spinnerSchoLevel);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerFacCountry);
        spinnerTotalExp=(Spinner)view.findViewById(R.id.spinnerTotalExp);
        spinnerIndusExp=(Spinner)view.findViewById(R.id.spinnerIndusExp);
        spinnerModeOfClass=(Spinner)view.findViewById(R.id.modeOfClass);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.select_school, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSchoLevel.setAdapter(arrayAdapter);
        spinnerSchoLevel.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterCountry = ArrayAdapter.createFromResource(getActivity(), R.array.country, android.R.layout.simple_spinner_item);
        arrayAdapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(arrayAdapterCountry);
        spinnerCountry.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterTotalExp=ArrayAdapter.createFromResource(getActivity(),R.array.total_exp,android.R.layout.simple_spinner_item);
        arrayAdapterTotalExp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTotalExp.setAdapter(arrayAdapterTotalExp);
        spinnerTotalExp.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterIndusExp=ArrayAdapter.createFromResource(getActivity(),R.array.indus_exp,android.R.layout.simple_spinner_item);
        arrayAdapterIndusExp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIndusExp.setAdapter(arrayAdapterTotalExp);
        spinnerIndusExp.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterModeOfClass=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        arrayAdapterModeOfClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModeOfClass.setAdapter(arrayAdapterTotalExp);
        spinnerModeOfClass.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        if (adapterView.getId() == R.id.spinnerSchoLevel) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("SelectedSchool " + selected);

        } else if (adapterView.getId() == R.id.spinnerFacCountry) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }else if (adapterView.getId() == R.id.spinnerTotalExp) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }else if (adapterView.getId() == R.id.spinnerIndusExp) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }else if (adapterView.getId() == R.id.modeOfClass) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
