package com.tech.tnqguru.retrofit;


import com.tech.tnqguru.modelresponse.BaseResponseDTO;
import com.tech.tnqguru.modelresponse.ColgStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.LoginResponseDTO;
import com.tech.tnqguru.modelresponse.ScholStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.UserDetailsForPayemntDTO;
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

    @GET("college_student/{userid}")
    Call<UserDetailsForPayemntDTO> getDetailsForColgStudent(@Path("userid") int userid);


    @GET("school_student/{userid}")
    Call<UserDetailsForPayemntDTO> getDetailsForScholStudent(@Path("userid") int userid);



    @GET("college_fees/")
    Call<List<ColgStuFeesResponseDTO>> getColgStuFeesDetails();

    @GET("school_fees/{std}/{board}")
    Call<List<ScholStuFeesResponseDTO>> getScholStuFeesDetails(@Path("std") String std, @Path("board") String board);

    @Multipart
    @POST("college_faculty/")
    Call<BaseResponseDTO> doCollegeFacRegistration(

            @Part("college_level") String facColg,
            @Part("faculty_name") String facName,
            @Part("faculty_email") String facEmail,
            @Part("faculty_phone") String facphone,
            @Part MultipartBody.Part facPhoto,

            @Part("faculty_country") String facCountry,
            @Part("faculty_address") String facAddress,
            @Part("faculty_pincode") String facPinCode,
            @Part("faculty_qualification[]") List<String> facQualification,

            @Part("teaching_experience") String facTeachExp,
            @Part("mode_of_class") String facModeOfClass,
            @Part MultipartBody.Part facBioData,
            @Part("subject[]")List<String> preMaxSubject,

            @Part("industrial_experience") String facIndusExp,
            @Part("about_faculty") String facAbtFac,

            @Part("kind_of_degree")String facDept,
            @Part("course_name[]")List<String> courseNameList,


            @Part MultipartBody.Part facIdProofDoucment,
            @Part("id_proof_document_number") String facDocNum,
            @Part MultipartBody.Part facBankDetails,


            @Part("username") String facUserName,
            @Part("password") String facPassword
            );

    @Multipart
    @POST("college_faculty/")
    Call<BaseResponseDTO> doDummyCollegeFacRegistration(

            @Part("college_level") RequestBody facColg,
            @Part("faculty_name") RequestBody facName,
            @Part("faculty_email") RequestBody facEmail,
            @Part("faculty_phone") RequestBody facphone,
            @Part ("faculty_photo") RequestBody facPhoto,

            @Part("faculty_country") RequestBody facCountry,
            @Part("faculty_address") RequestBody facAddress,
            @Part("faculty_pincode") RequestBody facPinCode,
            @Part("faculty_qualification") List<MultipartBody.Part> facQualification,

            @Part("teaching_experience") RequestBody facTeachExp,
            @Part("mode_of_class") RequestBody facModeOfClass,
            @Part ("bio_data_document")  RequestBody facBioData,
            @Part("subject")List<MultipartBody.Part> preMaxSubject,

            @Part("industrial_experience") RequestBody facIndusExp,
            @Part("about_faculty") RequestBody facAbtFac,

            @Part("kind_of_degree")RequestBody facDept,
            @Part("course_name")List<MultipartBody.Part> courseNameList,


            @Part ("id_proof_document")RequestBody facIdProofDoucment,
            @Part("id_proof_document_number") RequestBody facDocNum,
            @Part ("bank_details")RequestBody facBankDetails,


            @Part("username") RequestBody facUserName,
            @Part("password") RequestBody facPassword
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
            @Field("faculty_qualification") List<String> facQualification,

            @Field("teaching_experience") String facTeachExp,
            @Field("industrial_experience") String facIndusExp,
            @Field("mode_of_class") String facModeOfClass,
            @Field("")List<String> facPreferredSub,
            @Field("about_faculty") String facAbtFac,

            @Field("id_proof_document") String facIdProofDoucment,
            @Field("id_proof_document_number") String facDocNum,
            @Field("bio_data_document") String facBioData,
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
            @Field("school_level")  List<String> schoolStuMaxSubject,
            @Field("school_level")  String schoolStuImage,

            @Field("school_level") String syllabusDocument,
            @Field("school_level")  String userName,
            @Field("school_level")  String password


    );


 @FormUrlEncoded
 @POST("/")
 Call<BaseResponseDTO> doCollegeStudentRegistration(

         @Field("school_level")  String colgStuLevel,
         @Field("school_level")  String colgStuName,
         @Field("school_level")  String colgStuMobile,
         @Field("school_level")  String schoolStuDOB,
         @Field("school_level") String spinColgStuDept,

         @Field("school_level")  String schoolStuAddress,
         @Field("school_level")  String schoolStuPincode,
         @Field("school_level")  String schoolStuEmail,
         @Field("school_level")  String schoolStuModeOfClass,

         @Field("school_level")  List<String> schoolStuMaxSubject,
         @Field("school_level")  String schoolStuImage,
         @Field("school_level") String syllabusDocument,

         @Field("school_level")  String userName,
         @Field("school_level")  String password


 );


 @Multipart
    @POST("upload")
    Call<RequestBody> uploadImage(@Part MultipartBody.Part part, @Part("somedata") RequestBody requestBody);






}
