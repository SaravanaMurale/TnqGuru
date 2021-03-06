package com.tech.tnqguru.facultyfragment;

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

public class FacultyAccountFragment extends Fragment {


    Button facLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_faculty_account, container, false);

        facLogout=(Button)view.findViewById(R.id.facLogout);


        facLogout.setOnClickListener(new View.OnClickListener() {
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
