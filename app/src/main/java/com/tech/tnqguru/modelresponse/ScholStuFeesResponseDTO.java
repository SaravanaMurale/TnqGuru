package com.tech.tnqguru.modelresponse;

import com.google.gson.annotations.SerializedName;

public class ScholStuFeesResponseDTO {

    @SerializedName("degree")
    String scholStuDegree;
    @SerializedName("hours")
    String scholStuHours;
    @SerializedName("online_fees")
    String scholStuOnlineFees;
    @SerializedName("offline_fees")
    String scholStuOflineFees;

    public ScholStuFeesResponseDTO(String scholStuDegree, String scholStuHours, String scholStuOnlineFees, String scholStuOflineFees) {
        this.scholStuDegree = scholStuDegree;
        this.scholStuHours = scholStuHours;
        this.scholStuOnlineFees = scholStuOnlineFees;
        this.scholStuOflineFees = scholStuOflineFees;
    }

    public String getScholStuDegree() {
        return scholStuDegree;
    }

    public void setScholStuDegree(String scholStuDegree) {
        this.scholStuDegree = scholStuDegree;
    }

    public String getScholStuHours() {
        return scholStuHours;
    }

    public void setScholStuHours(String scholStuHours) {
        this.scholStuHours = scholStuHours;
    }

    public String getScholStuOnlineFees() {
        return scholStuOnlineFees;
    }

    public void setScholStuOnlineFees(String scholStuOnlineFees) {
        this.scholStuOnlineFees = scholStuOnlineFees;
    }

    public String getScholStuOflineFees() {
        return scholStuOflineFees;
    }

    public void setScholStuOflineFees(String scholStuOflineFees) {
        this.scholStuOflineFees = scholStuOflineFees;
    }
}
