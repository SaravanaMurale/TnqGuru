package com.tech.tnqguru.common;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.spinneradapter.SpinAdapter;
import com.tech.tnqguru.spinneradapter.SpinMaxSubAdapter;
import com.tech.tnqguru.utils.AppConstant;
import com.tech.tnqguru.utils.MathUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchoolStuRegFragment  extends Fragment implements AdapterView.OnItemSelectedListener, SpinMaxSubAdapter.SpinnerMaxSubCheckBoxSelectedListener {


    Spinner spinnerScholStuReg,spinnerScholStuModeOfClass,scholStuCoachingSub;
    String spnScholStuSelectSchol,spnScholStuSelectModeOfClass;
    Button btnScholStuReg;

    EditText scholStuNameEdit,scholStuMobileEdit,scholStuAddressEdit,scholStuPincodeEdit,scholStuEmailEdit,scholStuIdProofNumberEdit,scholStuPasswordEdit;

    TextView scholStuDOB;
    RelativeLayout stuDOBBlock;
    String scholStuDOBInString;

    DatePickerDialog.OnDateSetListener setListenerDateOfBirth;


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


        ArrayAdapter<CharSequence> selectSchool=ArrayAdapter.createFromResource(getActivity(),R.array.select_school,android.R.layout.simple_spinner_item);
        selectSchool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScholStuReg.setAdapter(selectSchool);
        spinnerScholStuReg.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> selectSchoolModeOfClass=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        selectSchoolModeOfClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScholStuModeOfClass.setAdapter(selectSchoolModeOfClass);
        spinnerScholStuModeOfClass.setOnItemSelectedListener(this);

        List<SpinAdapter> listVOs=new ArrayList<>();

        List<String> getColgMaxSub= AppConstant.getPrferredSubject();

        for (int i = 0; i < getColgMaxSub.size(); i++) {
            SpinAdapter stateVO = new SpinAdapter();
            stateVO.setTitle(getColgMaxSub.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        SpinMaxSubAdapter spinMaxSubAdapter = new SpinMaxSubAdapter(getActivity(), 0, listVOs, SchoolStuRegFragment.this);
        scholStuCoachingSub.setAdapter(spinMaxSubAdapter);




        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);

        stuDOBBlock.setOnClickListener(new View.OnClickListener() {
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
                scholStuDOB.setText(date);

                scholStuDOBInString=date;

                System.out.println("selectedFromDate " + date);


            }
        };

    }

    private void setView(View view) {

        spinnerScholStuReg = (Spinner) view.findViewById(R.id.spinnerSelectSchool);
        spinnerScholStuModeOfClass = (Spinner) view.findViewById(R.id.modeOfScholStuClass);
        scholStuCoachingSub=(Spinner)view.findViewById(R.id.scholStuCoachingSub);


        scholStuNameEdit=(EditText)view.findViewById(R.id.scholStuName);
        scholStuMobileEdit=(EditText)view.findViewById(R.id.scholStuMobile);
        scholStuAddressEdit=(EditText)view.findViewById(R.id.scholStuAddress);
        scholStuPincodeEdit=(EditText)view.findViewById(R.id.scholStuPincode);
        scholStuEmailEdit=(EditText)view.findViewById(R.id.scholStuEmail);
        scholStuPasswordEdit= (EditText)view.findViewById(R.id.scholStuPassword);
        //scholStuIdProofNumberEdit=(EditText)view.findViewById(R.id.colgStuPassword);

        stuDOBBlock=(RelativeLayout)view.findViewById(R.id.stuDOBBlock);
        scholStuDOB=(TextView)view.findViewById(R.id.scholStuDOB);

        btnScholStuReg=(Button) view.findViewById(R.id.btnScholStuReg);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.spinnerSelectSchool) {

            spnScholStuSelectSchol = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgUGorPG " + spnScholStuSelectSchol);

        } else if (adapterView.getId() == R.id.spinnerColgFacCountry) {

            spnScholStuSelectModeOfClass = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnScholStuSelectModeOfClass);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void selectMaxSpinnerCheckBox(String item, boolean status) {

    }
}
