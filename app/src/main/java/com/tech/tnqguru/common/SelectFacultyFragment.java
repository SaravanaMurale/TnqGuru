package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tech.tnqguru.R;

public class SelectFacultyFragment extends Fragment {

    private Button btn_fac_selection;
    private RadioGroup radioGroup;
    RadioButton facSeleBtn;

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

                        facSelected="SCHOOL";
                        System.out.println("RadioSchoolCheckd");

                        /*Fragment schoolFacRegFragment=new SchoolFacRegFragment();
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_register_container,schoolFacRegFragment);
                        fragmentTransaction.addToBackStack("fragmentB");
                        fragmentTransaction.commit();*/

                        break;

                    case R.id.radioCollege:

                        facSelected="COLLEGE";
                        System.out.println("RadioCollegeCheckd");

                        /*Fragment collegeFacRegFragment=new CollegeFacRegFragment();
                        FragmentManager fragmentManager1=getFragmentManager();
                        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
                        fragmentTransaction1.replace(R.id.fragment_register_container,collegeFacRegFragment);
                        fragmentTransaction1.addToBackStack("fragmentB");
                        fragmentTransaction1.commit();*/

                        break;

                }

            }
        });

        btn_fac_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment=null;

                if(facSelected.equals("SCHOOL")){
                    fragment=new SchoolFacRegFragment();

                }else if(facSelected.equals("COLLEGE")){
                    fragment=new CollegeFacRegFragment();
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

    public void checkBtn(View view){

        int selectedId = radioGroup.getCheckedRadioButtonId();
        facSeleBtn = (RadioButton)view.findViewById(selectedId);
        if(selectedId==-1){
            Toast.makeText(getActivity(),"Nothing selected", Toast.LENGTH_SHORT).show();
        }
        else{

            System.out.println("RadioBtnId"+selectedId);
            String facStatus= facSeleBtn.getText().toString();

            System.out.println("IDDDD"+facSeleBtn.getId());
            System.out.println("TEDDDT"+facSeleBtn.getText().toString());

            Toast.makeText(getActivity(),facSeleBtn.getText().toString(), Toast.LENGTH_SHORT).show();

                    if(facStatus.equals("School")){
                        Toast.makeText(getActivity(),"School Selected", Toast.LENGTH_SHORT).show();

                        Fragment schoolFacRegFragment=new SchoolFacRegFragment();
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_register_container,schoolFacRegFragment);
                        //fragmentTransaction.addToBackStack("fragmentB");
                        fragmentTransaction.commit();


                    }else {
                        Toast.makeText(getActivity(),"College Selected", Toast.LENGTH_SHORT).show();

                        Fragment collegeFacRegFragment=new CollegeFacRegFragment();
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_register_container,collegeFacRegFragment);
                        //fragmentTransaction.addToBackStack("fragmentB");
                        fragmentTransaction.commit();

                    }

        }

    }
}
