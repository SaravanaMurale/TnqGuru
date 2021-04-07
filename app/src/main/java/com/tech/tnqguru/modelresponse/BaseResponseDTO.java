package com.tech.tnqguru.modelresponse;

import com.google.gson.annotations.SerializedName;

public class BaseResponseDTO{

    @SerializedName("code")
    private int responseCode;
    @SerializedName("message")
    private String responseMessage;

    public BaseResponseDTO(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
