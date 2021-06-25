package com.tech.tnqguru.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.tnqguru.R;
import com.tech.tnqguru.modelresponse.ColgStuFeesResponseDTO;
import com.tech.tnqguru.modelresponse.ScholStuFeesResponseDTO;

import java.util.List;

public class ScholStuFeesAdapter extends RecyclerView.Adapter<ScholStuFeesAdapter.ScholStuViewHolder> {

    Context context;
    List<ScholStuFeesResponseDTO> scholStuFeesResponseDTOList;
    ScholFeesClickListener scholFeesClickListener;

    interface ScholFeesClickListener {
        public void scholFeesClick(String modeOfFees);
    }

    public ScholStuFeesAdapter(Context context, List<ScholStuFeesResponseDTO> scholStuFeesResponseDTOList, ScholFeesClickListener scholFeesClickListener) {
        this.context = context;
        this.scholStuFeesResponseDTOList = scholStuFeesResponseDTOList;
        this.scholFeesClickListener = scholFeesClickListener;
    }

    public void setData(List<ScholStuFeesResponseDTO> scholStuFeesResponseDTOList){
        this.scholStuFeesResponseDTOList=scholStuFeesResponseDTOList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScholStuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_schol_stu_fees, parent, false);
        return new ScholStuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholStuViewHolder holder, int position) {

        holder.scholHeadStd.setText(scholStuFeesResponseDTOList.get(position).getScholStuDegree());
        holder.scholHeadHours.setText(scholStuFeesResponseDTOList.get(position).getScholStuHours());
        holder.scholHeadOnlineFees.setText("₹"+scholStuFeesResponseDTOList.get(position).getScholStuOnlineFees());
        holder.scholHeadOflineFees.setText("₹"+scholStuFeesResponseDTOList.get(position).getScholStuOflineFees());

    }

    @Override
    public int getItemCount() {
        return scholStuFeesResponseDTOList.size();
    }

    class ScholStuViewHolder extends RecyclerView.ViewHolder  {

        TextView scholHeadStd,scholHeadHours,scholHeadOnlineFees,scholHeadOflineFees;

        public ScholStuViewHolder(@NonNull View itemView) {
            super(itemView);

            scholHeadStd = (TextView) itemView.findViewById(R.id.scholHeadStd);
            scholHeadHours = (TextView) itemView.findViewById(R.id.scholHeadHours);
            scholHeadOnlineFees = (TextView) itemView.findViewById(R.id.scholHeadOnlineFees);
            scholHeadOflineFees = (TextView) itemView.findViewById(R.id.scholHeadOflineFees);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView onLineSchol,offLineSchol;

                    Dialog builder = new Dialog(context);
                    View dialogView=LayoutInflater.from(context).inflate(R.layout.layout_online_offline_dialog, null);
                    builder.setContentView(dialogView);

                    onLineSchol = (TextView) dialogView.findViewById(R.id.onlineTExt);
                    offLineSchol = (TextView) dialogView.findViewById(R.id.offlineTExt);

                    onLineSchol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ScholStuFeesResponseDTO scholStuFeesResponseDTO=scholStuFeesResponseDTOList.get(getAdapterPosition());
                            scholFeesClickListener.scholFeesClick(scholStuFeesResponseDTO.getScholStuOnlineFees());
                            builder.dismiss();
                        }
                    });

                    offLineSchol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ScholStuFeesResponseDTO scholStuFeesResponseDTO=scholStuFeesResponseDTOList.get(getAdapterPosition());
                            scholFeesClickListener.scholFeesClick(scholStuFeesResponseDTO.getScholStuOflineFees());
                            builder.dismiss();
                        }
                    });



                    /*builder.setPositiveButton("ONLINE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ScholStuFeesResponseDTO scholStuFeesResponseDTO=scholStuFeesResponseDTOList.get(getAdapterPosition());
                            scholFeesClickListener.scholFeesClick(scholStuFeesResponseDTO.getScholStuOnlineFees());
                        }
                    });

                    builder.setNegativeButton("OFLINE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ScholStuFeesResponseDTO scholStuFeesResponseDTO=scholStuFeesResponseDTOList.get(getAdapterPosition());
                            scholFeesClickListener.scholFeesClick(scholStuFeesResponseDTO.getScholStuOflineFees());
                        }
                    });*/

                    builder.show();

                }
            });


        }
    }
}
