package com.tech.tnqguru.modelresponse;

import com.google.gson.annotations.SerializedName;

public class LoginResponseDTO {

    @SerializedName("sessionid")
    private String sessionId;
    @SerializedName("username")
    private String userName;
    @SerializedName("privilege")
    private String privilegeId;
    @SerializedName("role")
    private String userRole;
    @SerializedName("lstatus")
    private int lStatus;
    @SerializedName("message")
    private String loginMessage;



    public LoginResponseDTO(String sessionId, String userName, String privilegeId, String userRole, int lStatus, String loginMessage) {
        this.sessionId = sessionId;
        this.userName = userName;
        this.privilegeId = privilegeId;
        this.userRole = userRole;
        this.lStatus = lStatus;
        this.loginMessage = loginMessage;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getlStatus() {
        return lStatus;
    }

    public void setlStatus(int lStatus) {
        this.lStatus = lStatus;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}
