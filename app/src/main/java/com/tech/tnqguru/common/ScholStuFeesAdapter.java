package com.tech.tnqguru.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.ColgStuFeesResponseDTO;

import java.util.List;

public class ScholStuFeesAdapter extends RecyclerView.Adapter<ScholStuFeesAdapter.ScholStuViewHolder> {

    Context context;
    List<ColgStuFeesResponseDTO> colgStuFeesResponseDTOList;
    ColgStuFeesAdapter.ColgFeesClickListener colgFeesClickListener;


    @NonNull
    @Override
    public ScholStuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_schol_stu_fees, parent, false);
        return new ScholStuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholStuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ScholStuViewHolder extends RecyclerView.ViewHolder  {

        public ScholStuViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
