package com.tech.tnqguru.modelresponse;

import com.google.gson.annotations.SerializedName;

public class ColgStuFeesResponseDTO {

    @SerializedName("degree")
    String colgStuDegree;
    @SerializedName("hours")
    String colgStuHours;
    @SerializedName("online_fees")
    String colgStuOnlineFees;
    @SerializedName("offline_fees")
    String colgStuOflineFees;

    public ColgStuFeesResponseDTO(String colgStuDegree, String colgStuHours, String colgStuOnlineFees, String colgStuOflineFees) {
        this.colgStuDegree = colgStuDegree;
        this.colgStuHours = colgStuHours;
        this.colgStuOnlineFees = colgStuOnlineFees;
        this.colgStuOflineFees = colgStuOflineFees;
    }

    public String getColgStuDegree() {
        return colgStuDegree;
    }

    public void setColgStuDegree(String colgStuDegree) {
        this.colgStuDegree = colgStuDegree;
    }

    public String getColgStuHours() {
        return colgStuHours;
    }

    public void setColgStuHours(String colgStuHours) {
        this.colgStuHours = colgStuHours;
    }

    public String getColgStuOnlineFees() {
        return colgStuOnlineFees;
    }

    public void setColgStuOnlineFees(String colgStuOnlineFees) {
        this.colgStuOnlineFees = colgStuOnlineFees;
    }

    public String getColgStuOflineFees() {
        return colgStuOflineFees;
    }

    public void setColgStuOflineFees(String colgStuOflineFees) {
        this.colgStuOflineFees = colgStuOflineFees;
    }
}
