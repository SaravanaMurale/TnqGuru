package com.tech.tnqguru.modelresponse;

import com.google.gson.annotations.SerializedName;

public class UserDetailsForPayemntDTO {

    @SerializedName("student_name")
    private String userName;
    @SerializedName("student_phone")
    private String userMobile;
    @SerializedName("student_email")
    private String userEmail;

    public UserDetailsForPayemntDTO(String userName, String userMobile, String userEmail) {
        this.userName = userName;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
