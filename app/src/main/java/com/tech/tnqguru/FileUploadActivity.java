package com.tech.tnqguru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.retrofit.ApiClient;
import com.tech.tnqguru.retrofit.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUploadActivity extends AppCompatActivity {

    Button uplButn;
    String filePath = "/document/image:90098";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        uplButn=(Button)findViewById(R.id.uplButn);

        uplButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(filePath);

                ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);

                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part parts = MultipartBody.Part.createFormData("newimage", file.getName(), requestBody);

                RequestBody someData = RequestBody.create(MediaType.parse("text/plain"), "This is a new Image");

                Call call=apiInterface.uploadImage(parts, someData);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });


            }
        });
    }
}