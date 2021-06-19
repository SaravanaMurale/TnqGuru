package com.tech.tnqguru.studentfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.tech.tnqguru.R;
import com.tech.tnqguru.common.LoginActivity;
import com.tech.tnqguru.utils.PreferenceUtil;

public class StudentAccountFragment extends Fragment {

    Button stuLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_student_account, container, false);

        stuLogout=(Button)view.findViewById(R.id.stuLogout);

        stuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreferenceUtil.clear(getActivity());
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        });

        return view;
    }

}
