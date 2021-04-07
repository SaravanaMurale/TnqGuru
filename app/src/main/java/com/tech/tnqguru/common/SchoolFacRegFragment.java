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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.tech.tnqguru.utils.AppConstant.IMG_REQUEST;

public class SchoolFacRegFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerSchoLevel, spinnerCountry,spinnerTotalExp,spinnerIndusExp,spinnerModeOfClass,spinnerPreSubject;
    Button uploadFile,uploadImage;
    String mediaPath;
    Bitmap bitmap;

    private CheckBox cbBE,cbME,cbMS,cbBtech,cbMtech,cbMphil,cbPhd,cbBA,cbMA,cbBSC,cbMSC,cbMCA,cbBcom,cbMcom,cbOthers;
    private ArrayList<String> cbList;


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

        ArrayAdapter<CharSequence> arrayAdapterPreSubject=ArrayAdapter.createFromResource(getActivity(),R.array.pre_sub,android.R.layout.simple_spinner_item);
        arrayAdapterPreSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPreSubject.setAdapter(arrayAdapterTotalExp);
        spinnerPreSubject.setOnItemSelectedListener(this);



        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadFileData();
                
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToServer();
            }
        });


    }

    private void uploadImageToServer() {

        Intent intent = new Intent();
        intent.setType("image/*");
        //intent.setType("*/*"); For all kind of upload
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                //imageView.setImageBitmap(bitmap);
                startUploadToServer();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startUploadToServer() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage =  Base64.encodeToString(imageInByte, Base64.DEFAULT);

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

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("SelectedSchool " + selected);

        } else if (adapterView.getId() == R.id.spinnerScholFacCountry) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }else if (adapterView.getId() == R.id.spinnerScholTotalExp) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }else if (adapterView.getId() == R.id.spinnerScholIndusExp) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }else if (adapterView.getId() == R.id.spinnerScholmodeOfClass) {

            String selected = adapterView.getItemAtPosition(i).toString();
            System.out.println("countrySelected " + selected);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setView(View view) {

        spinnerSchoLevel = (Spinner) view.findViewById(R.id.spinnerSchoLevel);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerScholFacCountry);
        spinnerTotalExp=(Spinner)view.findViewById(R.id.spinnerScholTotalExp);
        spinnerIndusExp=(Spinner)view.findViewById(R.id.spinnerScholIndusExp);
        spinnerModeOfClass=(Spinner)view.findViewById(R.id.spinnerScholmodeOfClass);
        //spinnerPreSubject=(Spinner)view.findViewById(R.id.preSubject);

        /*uploadFile=(Button)view.findViewById(R.id.uploadFile);
        uploadImage=(Button)view.findViewById(R.id.uploadImage);*/

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

    }
}
