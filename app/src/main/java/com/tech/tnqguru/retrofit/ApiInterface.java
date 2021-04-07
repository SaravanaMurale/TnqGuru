package com.tech.tnqguru.retrofit;


import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.utils.AppConstant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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


    @GET("login/{email}/{password}")
    Call<LoginResponseDTO> doLogin(@Path("email") String email, @Path("password") String password);

    @FormUrlEncoded
    @POST("college_faculty/")
    Call<BaseResponseDTO> doCollegeFacRegistration(
            @Field("faculty_name") String facName,
            @Field("faculty_email") String facEmail,
            @Field("faculty_phone") String facphone,


            @Field("faculty_photo") String facPhoto,
            @Field("faculty_country") String facCountry,
            @Field("faculty_address") String facAddress,


            @Field("faculty_pincode") String facPinCode,
            @Field("faculty_qualification") String facQualification,
            @Field("teaching_experience") String facTeachExp,


            @Field("mode_of_class") String facModeOfClass,
            @Field("bio_data_document") String facBioData,
            @Field("subject") String facSubject,

            @Field("industrial_experience") String facIndusExp,
            @Field("about_faculty") String facAbtFac,
            @Field("kind_of_degree") String facDegree,


            @Field("course_name") String facCourseName,
            @Field("id_proof_document") String facIdProofDoucment,
            @Field("id_proof_document_number") String facDocNum,


            @Field("bank_details") String facBankDetails,
            @Field("username") String facUserName,
            @Field("password") String facPassword
            );






}
