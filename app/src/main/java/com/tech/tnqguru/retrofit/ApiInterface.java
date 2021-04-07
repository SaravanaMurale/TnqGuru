package com.tech.tnqguru.retrofit;


import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.utils.AppConstant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

   /* @POST(AppConstant.DOMAIN+"/getuser")
    Call<UserResponseDTO> getLoginUserDetails(@Body LoginDTO loginDTO);
*/

    @Multipart
    @POST("retrofit_example/upload_image.php")
   Call<ResponseBody> uploadFile(@Part MultipartBody.Part file,
                                 @Part("file") RequestBody name);


    //@GET("user/{username}/{password}")
    @GET("login/{email}/{password}")
    Call<LoginResponseDTO> doLogin(@Path("email") String email, @Path("password") String password);







}
