package com.tech.tnqguru.common;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;
import com.tech.tnqguru.spinneradapter.SpinAdapter;
import com.tech.tnqguru.spinneradapter.SpinMaxSubAdapter;
import com.tech.tnqguru.utils.AppConstant;
import com.tech.tnqguru.utils.LoaderUtil;
import com.tech.tnqguru.utils.MathUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.tech.tnqguru.utils.AppConstant.IMG_REQUEST;

public class SchoolStuRegFragment  extends Fragment implements AdapterView.OnItemSelectedListener, SpinMaxSubAdapter.SpinnerMaxSubCheckBoxSelectedListener {


    Spinner spinnerScholStuReg,spinnerScholStuModeOfClass,scholStuCoachingSub;
    String spnScholStuSelectSchol,spnScholStuSelectModeOfClass;
    Button scholStuUloadImage,btnScholStuReg;

    EditText scholStuNameEdit,scholStuMobileEdit,scholStuAddressEdit,scholStuPincodeEdit,scholStuEmailEdit,scholStuIdProofNumberEdit,scholStuPasswordEdit;

    TextView scholStuDOB,scholStuText;
    RelativeLayout stuDOBBlock;
    String scholStuDOBInString;

    DatePickerDialog.OnDateSetListener setListenerDateOfBirth;

    private Bitmap bitmap;

    private String scholStuImageInString;

    private List<String> maxSubject;




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


        if (scholStuName.isEmpty() || scholStuName.equals("") || scholStuName.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_LONG).show();
            return;

        }

        if (scholStuMobile.isEmpty() || scholStuMobile.equals("") || scholStuMobile.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Mobile", Toast.LENGTH_LONG).show();
            return;

        }

        if (scholStuAddress.isEmpty() || scholStuAddress.equals("") || scholStuAddress.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Address", Toast.LENGTH_LONG).show();
            return;

        }

        if (scholStuPincode.isEmpty() || scholStuPincode.equals("") || scholStuPincode.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Pincode", Toast.LENGTH_LONG).show();
            return;

        }


        if (scholStuEmail.isEmpty() || scholStuEmail.equals("") || scholStuEmail.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_LONG).show();
            return;

        }


        if (scholStuPassword.isEmpty() || scholStuPassword.equals("") || scholStuPassword.equals(null)) {
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
            return;

        }


        //doRegisterCollegeStudent(scholStuName,scholStuMobile,scholStuAddress,scholStuPincode,scholStuEmail,scholStuPassword);

    }


    private void doRegisterCollegeStudent(String scholStuName, String scholStuMobile, String scholStuAddress, String scholStuPincode, String scholStuEmail, String scholStuPassword) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<BaseResponseDTO> call=apiInterface.doSchoolStudentRegistration(
                spnScholStuSelectSchol,
                scholStuName,
                scholStuMobile,
                scholStuEmail,
                scholStuAddress,
                scholStuPincode,
                scholStuDOBInString,
                spnScholStuSelectModeOfClass,
                scholStuImageInString,
                maxSubject);

        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {

            }
        });




    }

    private void initView(View view) {

        setView(view);

        maxSubject=new ArrayList<>();


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

        scholStuUloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(1);
            }
        });

    }

    private void uploadImageToServer(int i) {

        Intent intent = new Intent();
        //intent.setType("image/*");
        intent.setType("*/*");  // For all kind of upload
        intent.setAction(Intent.ACTION_GET_CONTENT);
        IMG_REQUEST=i;
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();
            System.out.println("ImagePath"+path.getPath());


            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                //imageView.setImageBitmap(bitmap);
                startUploadToServer(requestCode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startUploadToServer(int imgRequest) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage =  Base64.encodeToString(imageInByte, Base64.DEFAULT);

        System.out.println("ImageInStringFormet"+encodedImage);

        if(imgRequest==1){
            //Faculty Photo
            scholStuText.setText("photo.jpeg");
            scholStuImageInString=encodedImage;
            //addColgFacImageInString.add(0,encodedImage);
        }

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
        scholStuText=(TextView)view.findViewById(R.id.scholStuText);

        scholStuUloadImage=(Button)view.findViewById(R.id.scholStuUloadImage);

        btnScholStuReg=(Button) view.findViewById(R.id.btnScholStuReg);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.spinnerSelectSchool) {

            spnScholStuSelectSchol = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgUGorPG " + spnScholStuSelectSchol);

        } else if (adapterView.getId() == R.id.modeOfScholStuClass) {

            spnScholStuSelectModeOfClass = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnScholStuSelectModeOfClass);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void selectMaxSpinnerCheckBox(String item, boolean status) {

        if(status){
            maxSubject.add(item);
        }else {
            maxSubject.remove(item);
        }

    }
}
