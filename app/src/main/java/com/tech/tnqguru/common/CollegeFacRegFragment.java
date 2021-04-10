package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollegeFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText colgFacNameEdit,colgFacMobileEdit,colgFacAddressEdit,colgFacPincodeEdit,colgFacEmailEdit,colgFacAboutEdit,colgFacSub1Edit,colgFacSub2Edit,colgFacSub3Edit,colgFacIdProofNumberEdit,colgFacPasswordEdit;

    Spinner spinnerSelectColgInput,spinnerColgFacCountryInput,spinnerColgFacDeptInput,spinnerColgTeachExpInput,spinnerColgIndusExpInput,modeOfColgClassInput;
    Button btnColFacReg;
    String spnColgFacSelectColg,spnColgFacSelectCountry,spnColgFacSelectDept,spnColgFacTechExp,spnColgFacIndusExp,spnColgFacModeOfClass;

    private CheckBox cbBE,cbME,cbMS,cbBtech,cbMtech,cbMphil,cbPhd,cbBA,cbMA,cbBSC,cbMSC,cbMCA,cbBcom,cbMcom,cbOthers;

    private ArrayList<String> cbList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.layout_college_fac_registration,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {


        setView(view);

        cbBE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbBE.isChecked()){
                    cbList.add("B.E");

                }else {
                    cbList.remove("B.E");

                }
            }
        });

        cbME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbME.isChecked()){
                    cbList.add("M.E");
                }else {
                    cbList.remove("M.E");
                }
            }
        });

        cbMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMS.isChecked()){
                    cbList.add("M.S");
                }else {
                    cbList.remove("M.S");
                }
            }
        });

        cbBtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbBtech.isChecked()){
                    cbList.add("B.Tech");
                }else {
                    cbList.remove("B.Tech");
                }
            }
        });

        cbMtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMtech.isChecked()){
                    cbList.add("M.Tech");
                }else {
                    cbList.remove("M.Tech");
                }
            }
        });

        cbMphil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMphil.isChecked()){
                    cbList.add("MPhil");
                }else {
                    cbList.remove("MPhil");
                }
            }
        });

        cbPhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbPhd.isChecked()){
                    cbList.add("Phd");
                }else {
                    cbList.remove("Phd");
                }
            }
        });

        cbBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbBA.isChecked()){
                    cbList.add("BA");
                }else {
                    cbList.remove("BA");
                }
            }
        });

        cbMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMA.isChecked()){
                    cbList.add("MA");
                }else {
                    cbList.remove("BA");
                }
            }
        });

        cbBSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbBSC.isChecked()){
                    cbList.add("BSC");
                }else {
                    cbList.remove("BSC");
                }
            }
        });

        cbMSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMSC.isChecked()){
                    cbList.add("MSC");
                }else {
                    cbList.remove("MSC");
                }
            }
        });

        cbMCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMCA.isChecked()){
                    cbList.add("MCA");
                }else {
                    cbList.remove("MCA");
                }
            }
        });

        cbBcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbBcom.isChecked()){
                    cbList.add("B.Com");
                }else {
                    cbList.remove("B.Com");
                }
            }
        });

        cbMcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbMcom.isChecked()){
                    cbList.add("M.Com");
                }else {
                    cbList.remove("M.Com");
                }
            }
        });

        cbOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cbOthers.isChecked()){
                    cbList.add("Others");
                }else {
                    cbList.remove("Others");
                }
            }
        });




        ArrayAdapter<CharSequence> selectColg=ArrayAdapter.createFromResource(getActivity(),R.array.colg_type,android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectColgInput.setAdapter(selectColg);
        spinnerSelectColgInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> countryAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.country,android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgFacCountryInput.setAdapter(countryAdapter);
        spinnerColgFacCountryInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> colgDeptAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.colg_dept,android.R.layout.simple_spinner_item);
        colgDeptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgFacDeptInput.setAdapter(colgDeptAdapter);
        spinnerColgFacDeptInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> teachExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.total_exp,android.R.layout.simple_spinner_item);
        teachExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgTeachExpInput.setAdapter(teachExpAdapter);
        spinnerColgTeachExpInput.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> indusExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.indus_exp,android.R.layout.simple_spinner_item);
        indusExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgIndusExpInput.setAdapter(indusExpAdapter);
        spinnerColgIndusExpInput.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> modeOfClassExpAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        modeOfClassExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeOfColgClassInput.setAdapter(modeOfClassExpAdapter);
        modeOfColgClassInput.setOnItemSelectedListener(this);

        btnColFacReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAllColgFacEnteredDetails();


            }
        });


    }



    private void doRegisterCollegeFaculty(String colgFacName, String colgFacMobile, String colgFacAddress, String colgFacPincode, String colgFacEmail, String colgFacPassword, String colgFacAbout, String colgFacSub1, String colgFacSub2, String colgFacSub3, String colgFacIdProofNumber) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<BaseResponseDTO> call=apiInterface.doCollegeFacRegistration(
                colgFacName,
                colgFacEmail,
                colgFacMobile,
                "photo.jpg",
                spnColgFacSelectCountry,
                colgFacAddress,
                colgFacPincode,
                "B.Tech",
                spnColgFacTechExp,
                spnColgFacModeOfClass,
                "BioData",
                "Fac Subject",
                spnColgFacIndusExp,
                colgFacAbout,
                "B.Tech",
                "Computer Science",
                "FacultyIdProof.jpeg",
                colgFacIdProofNumber,
                "FacBankDetails.jpeg",
                colgFacEmail,
                colgFacPassword);

        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

                BaseResponseDTO baseResponseDTO=response.body();
                System.out.println("RegistrationResponse"+baseResponseDTO.getResponseMessage()+" "+baseResponseDTO.getResponseCode());

                if(baseResponseDTO.getResponseCode()==200){
                    Toast.makeText(getActivity(),"Registered Successfully",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"Not Registered",Toast.LENGTH_LONG).show();
                }




            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {

                System.out.println("Exception"+t.getMessage().toString());

            }
        });



    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.spinnerSelectColg) {

            spnColgFacSelectColg = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgUGorPG " + spnColgFacSelectColg);

        } else if (adapterView.getId() == R.id.spinnerColgFacCountry) {

            spnColgFacSelectCountry = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnColgFacSelectCountry);
        }else if (adapterView.getId() == R.id.spinnerColgFacDept) {

            spnColgFacSelectDept = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacDe0t " + spnColgFacSelectDept);
        }else if (adapterView.getId() == R.id.spinnerColgTeachExp) {

            spnColgFacTechExp = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacTechExp " + spnColgFacTechExp);
        }else if(adapterView.getId() == R.id.spinnerColgIndusExp){
            spnColgFacIndusExp = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacIndusExp " + spnColgFacIndusExp);
        }

        else if (adapterView.getId() == R.id.modeOfColgClass) {
            spnColgFacModeOfClass = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacModeOfClass " + spnColgFacModeOfClass);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setView(View view) {


        colgFacNameEdit=(EditText)view.findViewById(R.id.ColgFacNameInput);
        colgFacMobileEdit=(EditText)view.findViewById(R.id.colgFacMobile);
        colgFacAddressEdit=(EditText)view.findViewById(R.id.colgFacAddress);
        colgFacPincodeEdit=(EditText)view.findViewById(R.id.colgFacPincode);
        colgFacEmailEdit=(EditText)view.findViewById(R.id.colgFacEmail);
        colgFacAboutEdit=(EditText)view.findViewById(R.id.colgFacAbout);
        colgFacSub1Edit=(EditText)view.findViewById(R.id.colgFacSub1);
        colgFacSub2Edit=(EditText)view.findViewById(R.id.colgFacSub2);
        colgFacSub3Edit=(EditText)view.findViewById(R.id.colgFacSub3);
        colgFacIdProofNumberEdit=(EditText)view.findViewById(R.id.colgFacIdProofNumber);
        colgFacPasswordEdit=(EditText)view.findViewById(R.id.colgFacPassword);

        spinnerSelectColgInput = (Spinner) view.findViewById(R.id.spinnerSelectColg);
        spinnerColgFacCountryInput = (Spinner) view.findViewById(R.id.spinnerColgFacCountry);
        spinnerColgFacDeptInput=(Spinner)view.findViewById(R.id.spinnerColgFacDept);
        spinnerColgTeachExpInput=(Spinner)view.findViewById(R.id.spinnerColgTeachExp);
        spinnerColgIndusExpInput=(Spinner)view.findViewById(R.id.spinnerColgIndusExp);
        modeOfColgClassInput=(Spinner)view.findViewById(R.id.modeOfColgClass);

        cbBE=(CheckBox)view.findViewById(R.id.be);
        cbME=(CheckBox)view.findViewById(R.id.me);
        cbMS=(CheckBox)view.findViewById(R.id.ms);
        cbBtech=(CheckBox)view.findViewById(R.id.btech);
        cbMtech=(CheckBox)view.findViewById(R.id.mtech);

        cbMphil=(CheckBox)view.findViewById(R.id.mphil);
        cbPhd=(CheckBox)view.findViewById(R.id.phd);
        cbBA=(CheckBox)view.findViewById(R.id.ba);
        cbMA=(CheckBox)view.findViewById(R.id.ma);
        cbBSC=(CheckBox)view.findViewById(R.id.bsc);

        cbMSC=(CheckBox)view.findViewById(R.id.msc);
        cbMCA=(CheckBox)view.findViewById(R.id.mca);
        cbBcom=(CheckBox)view.findViewById(R.id.bcom);
        cbMcom=(CheckBox)view.findViewById(R.id.mcom);
        cbOthers=(CheckBox)view.findViewById(R.id.others);

        cbList=new ArrayList<>();

        btnColFacReg=(Button)view.findViewById(R.id.btnColFacReg);

    }

    private void getAllColgFacEnteredDetails() {

        String colgFacName,colgFacMobile,colgFacAddress,colgFacPincode,colgFacEmail,colgFacPassword,colgFacAbout,colgFacSub1,colgFacSub2,colgFacSub3,colgFacIdProofNumber;

        colgFacName=colgFacNameEdit.getText().toString();
        colgFacMobile=colgFacMobileEdit.getText().toString();
        colgFacAddress=colgFacAddressEdit.getText().toString();
        colgFacPincode=colgFacPincodeEdit.getText().toString();
        colgFacEmail=colgFacEmailEdit.getText().toString();
        colgFacPassword=colgFacPasswordEdit.getText().toString();
        colgFacAbout=colgFacAboutEdit.getText().toString();
        colgFacSub1=colgFacSub1Edit.getText().toString();
        colgFacSub2=colgFacSub2Edit.getText().toString();
        colgFacSub3=colgFacSub3Edit.getText().toString();
        colgFacIdProofNumber=colgFacIdProofNumberEdit.getText().toString();

        System.out.println("EnteredData"+colgFacName+" "+colgFacMobile+" "+colgFacAddress+" "+colgFacPincode+" "+colgFacEmail+" "+colgFacAbout+" "+colgFacSub1+" "+colgFacSub2+" "+colgFacSub3+" "+colgFacIdProofNumber);

        doRegisterCollegeFaculty(colgFacName,colgFacMobile,colgFacAddress,colgFacPincode,colgFacEmail,colgFacPassword,colgFacAbout,colgFacSub1,colgFacSub2,colgFacSub3,colgFacIdProofNumber);


    }
}
