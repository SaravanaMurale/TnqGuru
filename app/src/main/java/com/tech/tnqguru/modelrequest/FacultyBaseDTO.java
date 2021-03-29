package com.tech.tnqguru.modelrequest;

import com.google.gson.annotations.SerializedName;

public class FacultyBaseDTO {

    @SerializedName("faculty_name")
    private String facultyName;

    @SerializedName("faculty_email")
    private String facultyEmail;

    @SerializedName("faculty_phone")
    private String facultyMobile;


    @SerializedName("faculty_photo")
    private String facultyPhoto;

    @SerializedName("faculty_country")
    private String facultyCountry;


    @SerializedName("faculty_address")
    private String facultyAddress;


    @SerializedName("faculty_pincode")
    private String facultyPincode;


    @SerializedName("faculty_qualification")
    private String facultyQualification;


    @SerializedName("teaching_experience")
    private String teachingExperience;


    @SerializedName("mode_of_class")
    private String modeOfClass;


    @SerializedName("bio_data_document")
    private String bioDataDocument;

    @SerializedName("subject")
    private String subject;

    @SerializedName("industrial_experience")
    private String industrial_exp;

    @SerializedName("about_faculty")
    private String aboutFaculty;


    @SerializedName("id_proof_document")
    private String id_proof_document;
    @SerializedName("id_proof_document_number")
    private String id_proof_document_number;

    @SerializedName("bank_details")
    private String bank_details;






}
