package com.tech.tnqguru.retrofit;


import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.modelresponse.ColgStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.modelresponse.ScholStuFeesResponseDTO;
import com.tech.tnqguru.utils.AppConstant;

import java.util.List;

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

    @GET("")
    Call<List<ColgStuFeesResponseDTO>> getColgStuFeesDetails();

    @GET("")
    Call<List<ScholStuFeesResponseDTO>> getScholStuFeesDetails();

    @FormUrlEncoded
    @POST("college_faculty/")
    Call<BaseResponseDTO> doCollegeFacRegistration(

            @Field("")String facColg,
            @Field("faculty_name") String facName,
            @Field("faculty_email") String facEmail,
            @Field("faculty_phone") String facphone,


            @Field("")String facDept,
            @Field("faculty_photo") String facPhoto,
            @Field("faculty_country") String facCountry,
            @Field("faculty_address") String facAddress,
            @Field("about_faculty") String facAbtFac,

            @Field("faculty_pincode") String facPinCode,
            @Field("faculty_qualification") List<String> facQualification,
            @Field("industrial_experience") String facIndusExp,
            @Field("teaching_experience") String facTeachExp,
            @Field("mode_of_class") String facModeOfClass,

            @Field("bio_data_document") String facBioData,
            @Field("subject") String facSubject1,
            @Field("subject") String facSubject2,
            @Field("subject") String facSubject3,
            @Field("")List<String> preMaxSubject,

            @Field("id_proof_document") String facIdProofDoucment,
            @Field("id_proof_document_number") String facDocNum,


            @Field("bank_details") String facBankDetails,
            @Field("username") String facUserName,
            @Field("password") String facPassword
            );


    @FormUrlEncoded
    @POST("/")
    Call<BaseResponseDTO> doSchoolFacRegistration(

            @Field("school_level")  String schoolLeve,
            @Field("faculty_name") String facName,
            @Field("faculty_email") String facEmail,
            @Field("faculty_phone") String facphone,

            @Field("faculty_photo") String facPhoto,
            @Field("faculty_country") String facCountry,
            @Field("faculty_address") String facAddress,

            @Field("faculty_pincode") String facPinCode,
            @Field("")List<String> facPreferredSub,
            @Field("faculty_qualification") List<String> facQualification,
            @Field("teaching_experience") String facTeachExp,

            @Field("mode_of_class") String facModeOfClass,
            @Field("bio_data_document") String facBioData,
            @Field("industrial_experience") String facIndusExp,

            @Field("about_faculty") String facAbtFac,
            @Field("id_proof_document") String facIdProofDoucment,
            @Field("id_proof_document_number") String facDocNum,

            @Field("bank_details") String facBankDetails,
            @Field("username") String facUserName,
            @Field("password") String facPassword
            );

    @FormUrlEncoded
    @POST("/")
    Call<BaseResponseDTO> doSchoolStudentRegistration(

            @Field("school_level")  String schoolStuLevel,
            @Field("school_level")  String schoolStuName,
            @Field("school_level")  String schoolStuMobile,
            @Field("school_level")  String schoolStuEmail,
            @Field("school_level")  String schoolStuAddress,
            @Field("school_level")  String schoolStuPincode,
            @Field("school_level")  String schoolStuDOB,
            @Field("school_level")  String schoolStuModeOfClass,
            @Field("school_level")  String schoolStuImage,
            @Field("school_level")  List<String> schoolStuMaxSubject


    );






}
