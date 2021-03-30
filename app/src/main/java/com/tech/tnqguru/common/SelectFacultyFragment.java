package com.tech.tnqguru.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.tnqguru.MainActivity;
import com.tech.tnqguru.R;

public class SelectFacultyFragment extends Fragment {

    private Button btn_fac_selection;
    private RadioGroup radioGroup;
    RadioButton facSeleBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_select_faculty_fragment, container, false);

        btn_fac_selection = (Button) view.findViewById(R.id.btn_fac_selection);
        radioGroup=(RadioGroup)view.findViewById(R.id.fac_sele_radioGroup);

        btn_fac_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                facSeleBtn = (RadioButton)view.findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(getActivity(),"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{

                    String facStatus= facSeleBtn.getText().toString();

                    Toast.makeText(getActivity(),facStatus, Toast.LENGTH_SHORT).show();

                    if(facStatus.equals("School")){
                        Toast.makeText(getActivity(),"School Selected", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),"College Selected", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return view;

    }
}
