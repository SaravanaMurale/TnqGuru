package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;

public class ColgStuRegFragment extends Fragment {

    Spinner spinnerSelectScholStu,spinnerScholStuModeOfClass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_colg_stu_reg,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        setView(view);

    }

    private void setView(View view) {

        spinnerSelectScholStu = (Spinner) view.findViewById(R.id.spinnerSelectColg);
        spinnerScholStuModeOfClass = (Spinner) view.findViewById(R.id.spinnerColgFacCountry);
    }
}
