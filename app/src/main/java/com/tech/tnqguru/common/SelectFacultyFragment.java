package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tech.tnqguru.R;

public class SelectFacultyFragment extends Fragment {

    private Button btn_fac_selection;
    private RadioGroup radioGroup;
    String facSelected=null;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_select_faculty_fragment, container, false);

        btn_fac_selection = (Button) view.findViewById(R.id.btn_fac_selection);
        radioGroup=(RadioGroup)view.findViewById(R.id.fac_sele_radioGroup);

        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){

                    case  R.id.radioSchool:

                        facSelected="FAC_SCHOOL";
                        System.out.println("RadioSchoolCheckd");

                        break;

                    case R.id.radioCollege:

                        facSelected="FAC_COLLEGE";
                        System.out.println("RadioCollegeCheckd");
                        break;

                    case  R.id.radioStuSchool:

                        facSelected="STU_SCHOOL";
                        System.out.println("RadioSchoolCheckd");

                        break;

                    case R.id.radioStuCollege:

                        facSelected="STU_COLLEGE";
                        System.out.println("RadioCollegeCheckd");
                        break;

                }

            }
        });

        btn_fac_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment=null;

                if(facSelected.equals("FAC_SCHOOL")){
                    fragment=new SchoolFacRegFragment();
                }else if(facSelected.equals("FAC_COLLEGE")){
                    fragment=new CollegeFacRegFragment();
                }else if(facSelected.equals("STU_SCHOOL")){
                    fragment=new SchoolStuRegFragment();
                }else if(facSelected.equals("STU_COLLEGE")){
                    fragment=new ColgStuRegFragment();
                }else if(facSelected==null){
                    System.out.println("Please Select");
                    return;
                }

                fragmentTransaction.replace(R.id.fragment_register_container,fragment);
                fragmentTransaction.addToBackStack("fragmentB");
                fragmentTransaction.commit();

            }
        });

        return view;

    }

}
