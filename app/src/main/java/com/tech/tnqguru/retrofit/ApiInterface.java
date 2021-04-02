package com.tech.tnqguru.retrofit;


import com.tech.tnqguru.utils.AppConstant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

   /* @POST(AppConstant.DOMAIN+"/getuser")
    Call<UserResponseDTO> getLoginUserDetails(@Body LoginDTO loginDTO);
*/

    @POST(AppConstant.DOMAIN+"/getuser")
   Call<ResponseBody> uploadFile(@Part MultipartBody.Part file,
                                 @Part("file") RequestBody name);






}
