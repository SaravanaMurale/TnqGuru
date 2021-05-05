package com.tech.tnqguru.utils;

import java.util.ArrayList;
import java.util.List;

public class AppConstant {

    public static final int RESOLVE_HINT = 1;
    public  static   int IMG_REQUEST = 21;

    public static final String DOMAIN="https://api.tnqguru.com/";

    public static List<String>   getPrferredSubject(){

        List<String> preferredSub=new ArrayList<>();
        preferredSub.add("Select Preference Subject");
        preferredSub.add("Mathematics");
        preferredSub.add("Science");
        preferredSub.add("Social Science");
        preferredSub.add("Phycis");
        preferredSub.add("Chemistry");
        preferredSub.add("Biology");
        preferredSub.add("Zoology");
        preferredSub.add("Accountancy");
        preferredSub.add("Computer Science");
        preferredSub.add("Commerce");
        preferredSub.add("Economies");
        preferredSub.add("English");
        preferredSub.add("French");

        return preferredSub;

    }


}
