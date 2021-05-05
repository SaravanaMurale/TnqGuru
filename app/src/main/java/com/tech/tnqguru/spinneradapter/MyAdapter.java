package com.tech.tnqguru.spinneradapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.tnqguru.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<SpinAdapter> {

    private Context mContext;
    private ArrayList<SpinAdapter> listState;
    private MyAdapter myAdapter;
    private boolean isFromView = false;
    ArrayList selectedItems = new ArrayList();

    SpinnerCheckBoxSelectedListener spinnerCheckBoxSelectedListener;

    public interface SpinnerCheckBoxSelectedListener{
        public void selectSpinnerCheckBox(String item,boolean status);
    }


    public MyAdapter(Context context, int resource, List<SpinAdapter> objects, SpinnerCheckBoxSelectedListener spinnerCheckBoxSelectedListener) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<SpinAdapter>) objects;
        this.myAdapter = this;
        this.spinnerCheckBoxSelectedListener=spinnerCheckBoxSelectedListener;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }

        holder.mCheckBox.setTag(position);

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                Log.d("Adapter","Buttonview   " + getPosition);

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);
                    if(listState.get(position).isSelected()){
                        Toast.makeText(getContext(),"Selected value " + listState.get(position).getTitle(), Toast.LENGTH_LONG).show();
                        spinnerCheckBoxSelectedListener.selectSpinnerCheckBox(listState.get(position).getTitle(),true);
                    }else {
                        spinnerCheckBoxSelectedListener.selectSpinnerCheckBox(listState.get(position).getTitle(),false);
                    }
                }

            }

        });





        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}
