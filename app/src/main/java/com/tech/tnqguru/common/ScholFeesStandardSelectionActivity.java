package com.tech.tnqguru.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.tech.tnqguru.R;

public class ScholFeesStandardSelectionActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private String stuSelectedStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schol_fees_standard_selection);

        radioGroup=(RadioGroup)findViewById(R.id.schol_std_radioGroup);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){

                    case  R.id.radioSchool:

                        stuSelectedStandard="IX";
                        //System.out.println("RadioSchoolCheckd");
                        break;

                    case R.id.radioCollege:

                        stuSelectedStandard="X";
                        //System.out.println("RadioCollegeCheckd");
                        break;

                    case  R.id.radioStuSchool:

                        stuSelectedStandard="XI";
                        //System.out.println("RadioSchoolCheckd");
                        break;

                    case R.id.radioStuCollege:

                        stuSelectedStandard="XII";
                        //System.out.println("RadioCollegeCheckd");
                        break;

                }

            }
        });




    }
}