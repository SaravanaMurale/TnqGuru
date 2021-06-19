package com.tech.tnqguru.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.tech.tnqguru.R;

public class ScholFeesStandardSelectionActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private String stuSelectedStandard;
    private Button btnscholFeesSelcSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schol_fees_standard_selection);

        radioGroup=(RadioGroup)findViewById(R.id.schol_std_radioGroup);
        btnscholFeesSelcSubmit=(Button)findViewById(R.id.btnscholFeesSelcSubmit);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){

                    case  R.id.radio9th:

                        stuSelectedStandard="IX/STATE";
                        //System.out.println("RadioSchoolCheckd");
                        setVisibility();

                        break;

                    case  R.id.radio9thcbse:

                        stuSelectedStandard="IX/CBSE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;


                    case  R.id.radio10th:

                        stuSelectedStandard="X/STATE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;

                    case  R.id.radio10thcbse:

                        stuSelectedStandard="X/CBSE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;


                    case  R.id.radio11th:

                        stuSelectedStandard="XI/STATE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;

                    case  R.id.radio11thcbse:

                        stuSelectedStandard="XI/CBSE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;


                    case  R.id.radio12th:

                        stuSelectedStandard="XII/STATE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;

                    case  R.id.radio12thcbse:

                        stuSelectedStandard="XII/CBSE";
                        setVisibility();
                        //System.out.println("RadioSchoolCheckd");
                        break;

                }

            }
        });

        btnscholFeesSelcSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ScholFeesStandardSelectionActivity.this,ScholStuFeesActivity.class);
                intent.putExtra("SELECTED_STD",stuSelectedStandard);
                startActivity(intent);
            }
        });


    }

    private void setVisibility() {
        btnscholFeesSelcSubmit.setVisibility(View.VISIBLE);
    }
}