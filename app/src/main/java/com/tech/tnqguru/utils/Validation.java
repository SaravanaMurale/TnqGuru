package com.tech.tnqguru.utils;

public class Validation {

    public static boolean validateName(String name) {

        if (name.length() < 3) {
            return false;
        }

        return true;

    }

}
