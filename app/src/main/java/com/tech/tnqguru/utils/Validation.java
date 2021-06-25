package com.tech.tnqguru.utils;

import android.widget.Toast;

import java.util.List;

public class Validation {

    public static boolean validateName(String name) {

        if (name.length() < 3) {
            return false;
        }

        return true;

    }

    public static boolean nullValidation(String formData){

        if (formData.isEmpty() || formData.equals("") || formData.equals(null)) {
           return true;
        }
        return false;

    }

    public static boolean listValidation(List<String> listData){

        if(listData.size()==0 ||listData.isEmpty() ){
            return true;
        }
        return false;

    }

}
