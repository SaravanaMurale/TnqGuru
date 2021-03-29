package com.tech.tnqguru.modelrequest;

import com.google.gson.annotations.SerializedName;

public class ColgFacultyDTO extends FacultyBaseDTO{


    @SerializedName("id")
    private String ColgFacId;
    @SerializedName("college_level")
    private String colgLevel;

    @SerializedName("kind_of_degree")
    private String kindOfDegree;

    @SerializedName("course_name")
    private String courseName;


}
