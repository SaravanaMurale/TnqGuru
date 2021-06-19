package com.tech.tnqguru.common;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColgStuFeesAdapter extends RecyclerView.Adapter<ColgStuFeesAdapter.ColgStuFeesViewHolder> {


    @NonNull
    @Override
    public ColgStuFeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ColgStuFeesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ColgStuFeesViewHolder extends RecyclerView.ViewHolder {

        public ColgStuFeesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
