package com.tech.tnqguru.common;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.tech.tnqguru.R;
import com.tech.tnqguru.spinneradapter.MyAdapter;
import com.tech.tnqguru.spinneradapter.SpinAdapter;
import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;
import com.tech.tnqguru.spinneradapter.SpinMaxDTO;
import com.tech.tnqguru.spinneradapter.SpinMaxSubAdapter;
import com.tech.tnqguru.utils.AppConstant;
import com.tech.tnqguru.utils.LoaderUtil;
import com.tech.tnqguru.utils.ToastUtils;
import com.tech.tnqguru.utils.Validation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

import static android.app.Activity.RESULT_OK;
import static com.tech.tnqguru.utils.AppConstant.IMG_REQUEST;

public class CollegeFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener, SpinMaxSubAdapter.SpinnerMaxSubCheckBoxSelectedListener {

    EditText colgFacNameEdit, colgFacMobileEdit, colgFacAddressEdit, colgFacPincodeEdit, colgFacEmailEdit, colgFacAboutEdit, colgFacSub1Edit, colgFacSub2Edit, colgFacSub3Edit, colgFacIdProofNumberEdit, colgFacPasswordEdit;

    Spinner spinnerSelectColgInput, spinnerColgFacCountryInput, spinnerColgFacDeptInput, spinnerColgTeachExpInput, spinnerColgIndusExpInput, modeOfColgClassInput, spinnerColgMaxSub;
    Button btnColFacReg;
    String spnColgFacSelectColg, spnColgFacSelectCountry, spnColgFacSelectDept, spnColgFacTechExp, spnColgFacIndusExp, spnColgFacModeOfClass;

    private CheckBox cbBE, cbME, cbMS, cbBtech, cbMtech, cbMphil, cbPhd, cbBA, cbMA, cbBSC, cbMSC, cbMCA, cbBcom, cbMcom, cbOthers;

    private ArrayList<String> cbList;
    MultipartBody.Part image1;

    private Button ColgUploadImage, colgFacIdProof, colgFacBankDetails;
    private TextView colgFacPhotoText, colgFacIdProofText, colgFactBankDetailText;
    private Bitmap bitmap;
    private List<String> addColgFacImageInString;

    private List<String> preferredMaxSubject;
    private List<String> courseNameList;

    RequestBody requestFile;
    RequestBody descBody;;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_college_fac_registration, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {


        setView(view);

        cbBE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbBE.isChecked()) {
                    cbList.add("B.E");

                } else {
                    cbList.remove("B.E");

                }
            }
        });

        cbME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbME.isChecked()) {
                    cbList.add("M.E");
                } else {
                    cbList.remove("M.E");
                }
            }
        });

        cbMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMS.isChecked()) {
                    cbList.add("M.S");
                } else {
                    cbList.remove("M.S");
                }
            }
        });

        cbBtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbBtech.isChecked()) {
                    cbList.add("B.Tech");
                } else {
                    cbList.remove("B.Tech");
                }
            }
        });

        cbMtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMtech.isChecked()) {
                    cbList.add("M.Tech");
                } else {
                    cbList.remove("M.Tech");
                }
            }
        });

        cbMphil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMphil.isChecked()) {
                    cbList.add("MPhil");
                } else {
                    cbList.remove("MPhil");
                }
            }
        });

        cbPhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbPhd.isChecked()) {
                    cbList.add("Phd");
                } else {
                    cbList.remove("Phd");
                }
            }
        });

        cbBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbBA.isChecked()) {
                    cbList.add("BA");
                } else {
                    cbList.remove("BA");
                }
            }
        });

        cbMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMA.isChecked()) {
                    cbList.add("MA");
                } else {
                    cbList.remove("BA");
                }
            }
        });

        cbBSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbBSC.isChecked()) {
                    cbList.add("BSC");
                } else {
                    cbList.remove("BSC");
                }
            }
        });

        cbMSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMSC.isChecked()) {
                    cbList.add("MSC");
                } else {
                    cbList.remove("MSC");
                }
            }
        });

        cbMCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMCA.isChecked()) {
                    cbList.add("MCA");
                } else {
                    cbList.remove("MCA");
                }
            }
        });

        cbBcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbBcom.isChecked()) {
                    cbList.add("B.Com");
                } else {
                    cbList.remove("B.Com");
                }
            }
        });

        cbMcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbMcom.isChecked()) {
                    cbList.add("M.Com");
                } else {
                    cbList.remove("M.Com");
                }
            }
        });

        cbOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cbOthers.isChecked()) {
                    cbList.add("Others");
                } else {
                    cbList.remove("Others");
                }
            }
        });


        ArrayAdapter<CharSequence> selectColg = ArrayAdapter.createFromResource(getActivity(), R.array.colg_type, android.R.layout.simple_spinner_item);
        selectColg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSelectColgInput.setAdapter(selectColg);
        spinnerSelectColgInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.country, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgFacCountryInput.setAdapter(countryAdapter);
        spinnerColgFacCountryInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> colgDeptAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.colg_dept, android.R.layout.simple_spinner_item);
        colgDeptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgFacDeptInput.setAdapter(colgDeptAdapter);
        spinnerColgFacDeptInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> teachExpAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.total_exp, android.R.layout.simple_spinner_item);
        teachExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgTeachExpInput.setAdapter(teachExpAdapter);
        spinnerColgTeachExpInput.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> indusExpAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.indus_exp, android.R.layout.simple_spinner_item);
        indusExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColgIndusExpInput.setAdapter(indusExpAdapter);
        spinnerColgIndusExpInput.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> modeOfClassExpAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.mode_class, android.R.layout.simple_spinner_item);
        modeOfClassExpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeOfColgClassInput.setAdapter(modeOfClassExpAdapter);
        modeOfColgClassInput.setOnItemSelectedListener(this);

        List<SpinAdapter> listVOs = new ArrayList<>();

        List<String> getColgMaxSub = AppConstant.getColgMaxSubject();

        for (int i = 0; i < getColgMaxSub.size(); i++) {
            SpinAdapter stateVO = new SpinAdapter();
            stateVO.setTitle(getColgMaxSub.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        SpinMaxSubAdapter spinMaxSubAdapter = new SpinMaxSubAdapter(getActivity(), 0, listVOs, CollegeFacRegFragment.this);
        spinnerColgMaxSub.setAdapter(spinMaxSubAdapter);


        ColgUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(1);
            }
        });

        colgFacIdProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(2);
            }
        });

        colgFacBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer(3);
            }
        });


        btnColFacReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doDummyRegistration();

               //getAllColgFacEnteredDetails();


            }
        });


    }

    private void doDummyRegistration() {

        //https://www.simplifiedcoding.net/retrofit-upload-file-tutorial/

        //https://www.youtube.com/watch?v=yKxLgEfY49A
        //https://stackoverflow.com/questions/40607862/retrofit-throwing-an-exception-java-lang-illegalargumentexception-only-one-en/40608320


        /*RequestBody emailRequest = RequestBody.create(MediaType.parse("text/plain"), "UG");
        RequestBody list = RequestBody.create(MediaType.parse("text/plain"), cbList);*/

       /* List<MultipartBody.Part> cbLists=new ArrayList<>();
        cbLists.add(MultipartBody.Part.createFormData("faculty_qualification", "cbList"));*/

        List<MultipartBody.Part> preferredMaxSubjects=new ArrayList<>();
        preferredMaxSubjects.add(MultipartBody.Part.createFormData("subject", "cbList"));

        List<MultipartBody.Part> courseNameLists=new ArrayList<>();
        courseNameLists.add(MultipartBody.Part.createFormData("course_name", "cbList"));



        String college_level="UG";
        String faculty_name="Srini";
        String faculty_email="srini3@gmail.com";
        String faculty_phone="9876543219";

        //String faculty_photo="UG";

        String faculty_country="India";
        String faculty_address="address";
        String faculty_pincode="607894";

        //String faculty_qualification="UG";

        String teaching_experience="5";
        String mode_of_class="Online";

        //String bio_data_document="UG";

       // String subject[]="UG";

        String industrial_experience="1";
        String about_faculty="About Faculty";
        String kind_of_degree="Kind Of Degree";

        //String course_name[]="UG";

        //String id_proof_document="UG";
        String id_proof_document_number="123456789";

        //String bank_details="UG";

        String username="srini";
        String password="srini@123";

        RequestBody emailRequest = RequestBody.create(MediaType.parse("text/plain"), faculty_name);

        List<MultipartBody.Part> cbLists=new ArrayList<>();
        cbLists.add(MultipartBody.Part.createFormData("faculty_qualification", "cbList"));

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<BaseResponseDTO> call = apiInterface.doDummyCollegeFacRegistration(
                emailRequest,
                emailRequest,
                emailRequest,
                emailRequest,
                requestFile,
                emailRequest,
                emailRequest,
                emailRequest,
                cbLists,
                emailRequest,
                emailRequest,
                requestFile,
                preferredMaxSubjects,
                emailRequest,
                emailRequest,
                emailRequest,
                courseNameLists,
                requestFile,
                emailRequest,
                requestFile,
                emailRequest,
                emailRequest);

        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

                BaseResponseDTO baseResponseDTO = response.body();
                System.out.println("RegistrationResponse" + baseResponseDTO.getResponseMessage() + " " + baseResponseDTO.getResponseCode());

                if (baseResponseDTO.getResponseCode() == 200) {
                    Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Not Registered", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {


                System.out.println("ErrorMessage"+t.getMessage().toString());


            }
        });


    }

    private void uploadImageToServer(int i) {

        //Intent intent = new Intent();
        //intent.setType("image/*");
       // intent.setType("*/*");  // For all kind of upload
       // intent.setAction(Intent.ACTION_GET_CONTENT);


        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        IMG_REQUEST = i;

        startActivityForResult(intent, IMG_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            System.out.println("ImagePath" + selectedImage.getPath());

            uploadFile(selectedImage, "My Image");

           // image1 = prepareImagePart(path);


            /*try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                //imageView.setImageBitmap(bitmap);
                startUploadToServer(requestCode);

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    private void uploadFile(Uri fileUri, String my_image) {

        //creating a file
        File file = new File(getRealPathFromURI(fileUri));

        //creating request body for file
        requestFile = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(fileUri)), file);
        descBody = RequestBody.create(MediaType.parse("text/plain"), my_image);

    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }



    private void startUploadToServer(int imgRequest) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);

        System.out.println("ImageInStringFormet" + encodedImage);

        if (imgRequest == 1) {
            //Faculty Photo
            colgFacPhotoText.setText("photo.jpeg");
            addColgFacImageInString.add(0, encodedImage);
        } else if (imgRequest == 2) {
            //Faculty ID Proof
            colgFacIdProofText.setText("idproof.jpeg");
            addColgFacImageInString.add(1, encodedImage);
        } else if (imgRequest == 3) {
            //Faculty Bank Details
            colgFactBankDetailText.setText("bankdetails.jpg");
            addColgFacImageInString.add(2, encodedImage);
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.spinnerSelectColg) {

            spnColgFacSelectColg = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgUGorPG " + spnColgFacSelectColg);

        } else if (adapterView.getId() == R.id.spinnerColgFacCountry) {

            spnColgFacSelectCountry = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + spnColgFacSelectCountry);
        } else if (adapterView.getId() == R.id.spinnerColgFacDept) {

            spnColgFacSelectDept = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacDe0t " + spnColgFacSelectDept);
        } else if (adapterView.getId() == R.id.spinnerColgTeachExp) {

            spnColgFacTechExp = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacTechExp " + spnColgFacTechExp);
        } else if (adapterView.getId() == R.id.spinnerColgIndusExp) {
            spnColgFacIndusExp = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacIndusExp " + spnColgFacIndusExp);
        } else if (adapterView.getId() == R.id.modeOfColgClass) {
            spnColgFacModeOfClass = adapterView.getItemAtPosition(i).toString();
            System.out.println("ColgFacModeOfClass " + spnColgFacModeOfClass);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void getAllColgFacEnteredDetails() {

        String colgFacName, colgFacMobile, colgFacAddress, colgFacPincode, colgFacEmail, colgFacPassword, colgFacAbout, colgFacSub1, colgFacSub2, colgFacSub3, colgFacIdProofNumber;

        colgFacName = colgFacNameEdit.getText().toString();
        colgFacMobile = colgFacMobileEdit.getText().toString();
        colgFacAddress = colgFacAddressEdit.getText().toString();
        colgFacPincode = colgFacPincodeEdit.getText().toString();
        colgFacEmail = colgFacEmailEdit.getText().toString();
        colgFacPassword = colgFacPasswordEdit.getText().toString();
        colgFacAbout = colgFacAboutEdit.getText().toString();
        colgFacSub1 = colgFacSub1Edit.getText().toString();
        colgFacSub2 = colgFacSub2Edit.getText().toString();
        colgFacSub3 = colgFacSub3Edit.getText().toString();
        colgFacIdProofNumber = colgFacIdProofNumberEdit.getText().toString();


        colgFacvalidation(colgFacName, colgFacMobile, colgFacAddress, colgFacPincode, colgFacEmail, colgFacPassword, colgFacAbout, colgFacSub1, colgFacSub2, colgFacSub3, colgFacIdProofNumber);


        //System.out.println("EnteredData"+colgFacName+" "+colgFacMobile+" "+colgFacAddress+" "+colgFacPincode+" "+colgFacEmail+" "+colgFacAbout+" "+colgFacSub1+" "+colgFacSub2+" "+colgFacSub3+" "+colgFacIdProofNumber);

    }

    private void colgFacvalidation(String colgFacName, String colgFacMobile, String colgFacAddress, String colgFacPincode, String colgFacEmail, String colgFacPassword, String colgFacAbout, String colgFacSub1, String colgFacSub2, String colgFacSub3, String colgFacIdProofNumber) {

        if (Validation.nullValidation(spnColgFacSelectColg)) {
            Toast.makeText(getActivity(), "Please Select College", Toast.LENGTH_LONG).show();
            return;
        }


        if (Validation.nullValidation(colgFacName)) {
            Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacEmail)) {
            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacMobile)) {
            Toast.makeText(getActivity(), "Please Enter Mobile Number", Toast.LENGTH_LONG).show();
            return;
        }

        //photo validation

        if (Validation.nullValidation(spnColgFacSelectCountry)) {
            Toast.makeText(getActivity(), "Please Select Country", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacAddress)) {
            Toast.makeText(getActivity(), "Please Enter Address", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacPincode)) {
            Toast.makeText(getActivity(), "Please Enter Pincode", Toast.LENGTH_LONG).show();
            return;
        }


        if (Validation.nullValidation(colgFacPincode)) {
            Toast.makeText(getActivity(), "Please Enter Pincode", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.listValidation(cbList)) {
            Toast.makeText(getActivity(), "Please Select Qualification", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(spnColgFacTechExp)) {
            Toast.makeText(getActivity(), "Please Select Technical Experience", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(spnColgFacModeOfClass)) {
            Toast.makeText(getActivity(), "Please Select Class Mode", Toast.LENGTH_LONG).show();
            return;
        }

        //Bio Data Validation

        if (Validation.listValidation(preferredMaxSubject)) {
            Toast.makeText(getActivity(), "Please Select Subject", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(spnColgFacIndusExp)) {
            Toast.makeText(getActivity(), "Please Select Industrical Experience", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacAbout)) {
            Toast.makeText(getActivity(), "Please Select About Yourself", Toast.LENGTH_LONG).show();
            return;
        }


        if (Validation.nullValidation(spnColgFacSelectDept)) {
            Toast.makeText(getActivity(), "Please Select Department", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacSub1)) {
            Toast.makeText(getActivity(), "Please Enter Subect1", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacSub2)) {
            Toast.makeText(getActivity(), "Please Enter Subect2", Toast.LENGTH_LONG).show();
            return;
        }

        if (Validation.nullValidation(colgFacSub3)) {
            Toast.makeText(getActivity(), "Please Enter Subect3", Toast.LENGTH_LONG).show();
            return;
        }

        //ID Proof Document

        if (Validation.nullValidation(colgFacIdProofNumber)) {
            Toast.makeText(getActivity(), "Please Enter ID Proof Number", Toast.LENGTH_LONG).show();
            return;
        }

        //Bank Document

        if (Validation.nullValidation(colgFacPassword)) {
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }

        courseNameList.add(colgFacSub1);
        courseNameList.add(colgFacSub2);
        courseNameList.add(colgFacSub3);

        doRegisterCollegeFaculty(colgFacName, colgFacMobile, colgFacAddress, colgFacPincode, colgFacEmail, colgFacPassword, colgFacAbout, courseNameList, colgFacIdProofNumber);


    }

    private void doRegisterCollegeFaculty(String colgFacName, String colgFacMobile, String colgFacAddress, String colgFacPincode, String colgFacEmail, String colgFacPassword, String colgFacAbout, List<String> courseNameList, String colgFacIdProofNumber) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<BaseResponseDTO> call = apiInterface.doCollegeFacRegistration(
                spnColgFacSelectColg,
                colgFacName,
                colgFacEmail,
                colgFacMobile,
                image1,
                spnColgFacSelectCountry,
                colgFacAddress,
                colgFacPincode,
                cbList,
                spnColgFacTechExp,
                spnColgFacModeOfClass,
                image1,
                preferredMaxSubject,
                spnColgFacIndusExp,
                colgFacAbout,
                spnColgFacSelectDept,
                courseNameList,
                image1,
                colgFacIdProofNumber,
                image1,
                colgFacEmail,
                colgFacPassword);

        call.enqueue(new Callback<BaseResponseDTO>() {
            @Override
            public void onResponse(Call<BaseResponseDTO> call, Response<BaseResponseDTO> response) {

                BaseResponseDTO baseResponseDTO = response.body();
                System.out.println("RegistrationResponse" + baseResponseDTO.getResponseMessage() + " " + baseResponseDTO.getResponseCode());

                if (baseResponseDTO.getResponseCode() == 200) {
                    Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Not Registered", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BaseResponseDTO> call, Throwable t) {

                System.out.println("Exception" + t.getMessage().toString());

            }
        });


    }


    private void setView(View view) {

        addColgFacImageInString = new ArrayList<>();
        preferredMaxSubject = new ArrayList<>();
        courseNameList = new ArrayList<>();

        colgFacNameEdit = (EditText) view.findViewById(R.id.ColgFacNameInput);
        colgFacMobileEdit = (EditText) view.findViewById(R.id.colgFacMobile);
        colgFacAddressEdit = (EditText) view.findViewById(R.id.colgFacAddress);
        colgFacPincodeEdit = (EditText) view.findViewById(R.id.colgFacPincode);
        colgFacEmailEdit = (EditText) view.findViewById(R.id.colgFacEmail);
        colgFacAboutEdit = (EditText) view.findViewById(R.id.colgFacAbout);
        colgFacSub1Edit = (EditText) view.findViewById(R.id.colgFacSub1);
        colgFacSub2Edit = (EditText) view.findViewById(R.id.colgFacSub2);
        colgFacSub3Edit = (EditText) view.findViewById(R.id.colgFacSub3);
        colgFacIdProofNumberEdit = (EditText) view.findViewById(R.id.colgFacIdProofNumber);
        colgFacPasswordEdit = (EditText) view.findViewById(R.id.colgFacPassword);

        spinnerSelectColgInput = (Spinner) view.findViewById(R.id.spinnerSelectColg);

        spinnerColgFacCountryInput = (Spinner) view.findViewById(R.id.spinnerColgFacCountry);
        spinnerColgFacDeptInput = (Spinner) view.findViewById(R.id.spinnerColgFacDept);
        spinnerColgTeachExpInput = (Spinner) view.findViewById(R.id.spinnerColgTeachExp);
        spinnerColgIndusExpInput = (Spinner) view.findViewById(R.id.spinnerColgIndusExp);
        modeOfColgClassInput = (Spinner) view.findViewById(R.id.modeOfColgClass);
        spinnerColgMaxSub = (Spinner) view.findViewById(R.id.spinnerColgMaxSub);

        cbBE = (CheckBox) view.findViewById(R.id.be);
        cbME = (CheckBox) view.findViewById(R.id.me);
        cbMS = (CheckBox) view.findViewById(R.id.ms);
        cbBtech = (CheckBox) view.findViewById(R.id.btech);
        cbMtech = (CheckBox) view.findViewById(R.id.mtech);

        cbMphil = (CheckBox) view.findViewById(R.id.mphil);
        cbPhd = (CheckBox) view.findViewById(R.id.phd);
        cbBA = (CheckBox) view.findViewById(R.id.ba);
        cbMA = (CheckBox) view.findViewById(R.id.ma);
        cbBSC = (CheckBox) view.findViewById(R.id.bsc);

        cbMSC = (CheckBox) view.findViewById(R.id.msc);
        cbMCA = (CheckBox) view.findViewById(R.id.mca);
        cbBcom = (CheckBox) view.findViewById(R.id.bcom);
        cbMcom = (CheckBox) view.findViewById(R.id.mcom);
        cbOthers = (CheckBox) view.findViewById(R.id.others);

        cbList = new ArrayList<>();


        ColgUploadImage = (Button) view.findViewById(R.id.ColgUploadImage);
        colgFacIdProof = (Button) view.findViewById(R.id.colgFacIdProof);
        colgFacBankDetails = (Button) view.findViewById(R.id.colgFacBankDetails);

        colgFacPhotoText = (TextView) view.findViewById(R.id.colgFacPhotoText);
        colgFacIdProofText = (TextView) view.findViewById(R.id.colgFacIdProofText);
        colgFactBankDetailText = (TextView) view.findViewById(R.id.colgFactBankDetailText);


        btnColFacReg = (Button) view.findViewById(R.id.btnColFacReg);

    }


    @Override
    public void selectMaxSpinnerCheckBox(String item, boolean status) {

        if (status) {
            preferredMaxSubject.add(item);
        } else {
            preferredMaxSubject.remove(item);
        }

    }

    private MultipartBody.Part prepareImagePart(Uri path) {

        String imageName="Img";
        File file = new File(path.getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse(getActivity().getApplicationContext().getContentResolver().getType(path)),file);
        return MultipartBody.Part.createFormData(imageName, file.getName(), requestBody);

    }
}
