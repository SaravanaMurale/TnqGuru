package com.tech.tnqguru.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelrequest.SpinAdapter;
import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;
import com.tech.tnqguru.utils.AppConstant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.tech.tnqguru.utils.AppConstant.IMG_REQUEST;

public class SchoolFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener, MyAdapter.SpinnerCheckBoxSelectedListener {


    private EditText scholFacNameEdit,scholFacMobileEdit,scholFacAddressEdit,scholFacPincodeEdit,scholFacEmailEdit,scholFacAboutEdit,scholFacIdProofNumberEdit,scholFacPasswordEdit;

    private Spinner spinnerSchoLevelInput, spinnerCountryInput,spinnerTotalExpInput,spinnerIndusExpInput,spinnerModeOfClassInput,spinnerScholPreSub;

    private String spnScholFacSelectColg,spnScholFacSelectCountry,spnScholFacTechExp,spnScholFacIndusExp,spnScholFacModeOfClass;

    private Button uploadFile,uploadImage,btnScholFacReg;

    private Button scholFacUploadImage,scholFacIdProof,scholFacBankDetails;
    List<String> addImageInString;

    String mediaPath;
    Bitmap bitmap;

    TextView schoolFacPhotoText,scholFacIdProofText,scholFacBioDataText,scholFactBankDetailText;

    private CheckBox cbBE,cbME,cbMS,cbBtech,cbMtech,cbMphil,cbPhd,cbBA,cbMA,cbBSC,cbMSC,cbMCA,cbBcom,cbMcom,cbOthers;
    private ArrayList<String> cbList;
    private String preferredSubject;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_school_fac_registration, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        setView(view);


        cbList=new ArrayList<>();

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


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.select_school, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSchoLevelInput.setAdapter(arrayAdapter);
        spinnerSchoLevelInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterCountry = ArrayAdapter.createFromResource(getActivity(), R.array.country, android.R.layout.simple_spinner_item);
        arrayAdapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountryInput.setAdapter(arrayAdapterCountry);
        spinnerCountryInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterTotalExp=ArrayAdapter.createFromResource(getActivity(),R.array.total_exp,android.R.layout.simple_spinner_item);
        arrayAdapterTotalExp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTotalExpInput.setAdapter(arrayAdapterTotalExp);
        spinnerTotalExpInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterIndusExp=ArrayAdapter.createFromResource(getActivity(),R.array.indus_exp,android.R.layout.simple_spinner_item);
        arrayAdapterIndusExp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIndusExpInput.setAdapter(arrayAdapterIndusExp);
        spinnerIndusExpInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapterModeOfClass=ArrayAdapter.createFromResource(getActivity(),R.array.mode_class,android.R.layout.simple_spinner_item);
        arrayAdapterModeOfClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModeOfClassInput.setAdapter(arrayAdapterModeOfClass);
        spinnerModeOfClassInput.setOnItemSelectedListener(this);

        List<SpinAdapter> listVOs=new ArrayList<>();

        List<String> getPreSubName= AppConstant.getPrferredSubject();

        for (int i = 0; i < getPreSubName.size(); i++) {
            SpinAdapter stateVO = new SpinAdapter();
            stateVO.setTitle(getPreSubName.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        MyAdapter myAdapter = new MyAdapter(getActivity(), 0, listVOs, SchoolFacRegFragment.this);
        spinnerScholPreSub.setAdapter(myAdapter);

        /*ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getPreSubName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerScholPreSub.setAdapter(adapter);
        spinnerScholPreSub.setOnItemSelectedListener(this);*/




        /*uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadFileData();
                
            }
        });*/

        scholFacUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(1);
            }
        });

        scholFacIdProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(2);
            }
        });

        scholFacBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(3);
            }
        });


        btnScholFacReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllScholFacEnteredDetails();
            }
        });


    }

    private void getAllScholFacEnteredDetails() {

        String scholFacName,scholFacMobile,scholFacAddress,scholFacPincode,scholFacEmail,scholFacPassword,scholFacAbout,scholFacIdProofNumber;

        scholFacName=scholFacNameEdit.getText().toString();
        scholFacMobile=scholFacMobileEdit.getText().toString();
        scholFacAddress=scholFacAddressEdit.getText().toString();
        scholFacPincode=scholFacPincodeEdit.getText().toString();
        scholFacEmail=scholFacEmailEdit.getText().toString();
        scholFacPassword=scholFacPasswordEdit.getText().toString();
        scholFacAbout=scholFacAboutEdit.getText().toString();
        scholFacIdProofNumber=scholFacIdProofNumberEdit.getText().toString();

        System.out.println("EnteredData"+scholFacName+" "+scholFacMobile+" "+scholFacAddress+" "+scholFacPincode+" "+scholFacEmail+" "+scholFacAbout+" "+scholFacIdProofNumber);

       doRegisterCollegeFaculty(scholFacName,scholFacMobile,scholFacAddress,scholFacPincode,scholFacEmail,scholFacPassword,scholFacAbout,scholFacIdProofNumber);

    }

    private void doRegisterCollegeFaculty(String scholFacName, String scholFacMobile, String scholFacAddress, String scholFacPincode, String scholFacEmail, String scholFacPassword, String scholFacAbout, String scholFacIdProofNumber) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<BaseResponseDTO> call= apiInterface.doSchoolFacRegistration(
                spnScholFacSelectColg,
                scholFacName,
                scholFacEmail,
                scholFacMobile,
                addImageInString.get(0),
                spnScholFacSelectCountry,
                scholFacAddress,
                scholFacPincode,
                preferredSubject,
                spnScholFacTechExp,
                spnScholFacModeOfClass,
                "BioData",
                spnScholFacIndusExp,
                scholFacAbout,
                addImageInString.get(1),
                scholFacIdProofNumber,
                addImageInString.get(2),
                scholFacEmail,
                scholFacPassword);


        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {

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
            schoolFacPhotoText.setText("photo.jpeg");
            addImageInString.add(0,encodedImage);
        }else if(imgRequest==2){
            //Faculty ID Proof
            scholFacIdProofText.setText("idproof.jpeg");
            addImageInString.add(1,encodedImage);
        } else if(imgRequest==3){
            //Faculty Bank Details
            scholFactBankDetailText.setText("bankdetails.jpg");
            addImageInString.add(2,encodedImage);
        }


        /*Call<ResponsePOJO> call = RetroClient.getInstance().getApi().uploadImage(encodedImage);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                Toast.makeText(MainActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();

                if(response.body().isStatus()){

                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private void uploadFileData() {

        // Map is used to multipart the file using okhttp3.RequestBody
        //File file = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_20210317-161601.png");
        File file = new File("/storage/emulated/0/Download/SampleDocument.odt");


        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<ResponseBody> call=apiInterface.uploadFile(fileToUpload,filename);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                System.out.println("UploadedFile");

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println("Exception"+t.getMessage().toString());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        if (adapterView.getId() == R.id.spinnerSchoLevel) {

            spnScholFacSelectColg = adapterView.getItemAtPosition(i).toString();
            System.out.println("SelectedSchool " + spnScholFacSelectColg);

        } else if (adapterView.getId() == R.id.spinnerScholFacCountry) {

            spnScholFacSelectCountry = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnScholFacSelectCountry);
        }else if (adapterView.getId() == R.id.spinnerScholTotalExp) {

            spnScholFacTechExp = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnScholFacTechExp);
        }else if (adapterView.getId() == R.id.spinnerScholIndusExp) {

            spnScholFacIndusExp = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnScholFacIndusExp);
        }else if (adapterView.getId() == R.id.spinnerScholmodeOfClass) {

            spnScholFacModeOfClass = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnScholFacModeOfClass);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setView(View view) {

        addImageInString=new ArrayList<>();

        scholFacNameEdit=(EditText)view.findViewById(R.id.scholFacName);
        scholFacMobileEdit=(EditText)view.findViewById(R.id.scholFacMobile);
        scholFacAddressEdit=(EditText)view.findViewById(R.id.scholFacAddress);
        scholFacPincodeEdit=(EditText)view.findViewById(R.id.scholFacPincode);
        scholFacEmailEdit=(EditText)view.findViewById(R.id.scholFacEmail);
        scholFacAboutEdit=(EditText)view.findViewById(R.id.scholFacAbout);
        scholFacIdProofNumberEdit=(EditText)view.findViewById(R.id.scholFacIdProofNumber);
        scholFacPasswordEdit=(EditText)view.findViewById(R.id.scholFacPassword);


        spinnerSchoLevelInput = (Spinner) view.findViewById(R.id.spinnerSchoLevel);
        spinnerCountryInput = (Spinner) view.findViewById(R.id.spinnerScholFacCountry);
        spinnerTotalExpInput=(Spinner)view.findViewById(R.id.spinnerScholTotalExp);
        spinnerIndusExpInput=(Spinner)view.findViewById(R.id.spinnerScholIndusExp);
        spinnerModeOfClassInput=(Spinner)view.findViewById(R.id.spinnerScholmodeOfClass);
        spinnerScholPreSub=(Spinner)view.findViewById(R.id.spinnerScholPreSub);

        /*uploadFile=(Button)view.findViewById(R.id.uploadFile);*/
        uploadImage=(Button)view.findViewById(R.id.scholFacBankDetails);
        scholFacUploadImage=(Button)view.findViewById(R.id.scholUploadImage);
        scholFacIdProof=(Button)view.findViewById(R.id.scholIdProof);
        scholFacBankDetails=(Button)view.findViewById(R.id.scholFacBankDetails);

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

        schoolFacPhotoText=(TextView)view.findViewById(R.id.schoolFacPhotoText);
        scholFacIdProofText=(TextView)view.findViewById(R.id.scholFacIdProofText);
        scholFacBioDataText=(TextView)view.findViewById(R.id.scholFacBioDataText);
        scholFactBankDetailText=(TextView)view.findViewById(R.id.scholFactBankDetailText);

        btnScholFacReg=(Button)view.findViewById(R.id.btnScholFacReg);

    }

    @Override
    public void selectSpinnerCheckBox(String item) {

        preferredSubject=item;

        System.out.println("SelectedPreferenceValued"+item);

    }
}
