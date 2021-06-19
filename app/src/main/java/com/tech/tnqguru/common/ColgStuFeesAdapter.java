package com.tech.tnqguru.common;

import android.app.AlertDialog;
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

import java.util.List;

public class ColgStuFeesAdapter extends RecyclerView.Adapter<ColgStuFeesAdapter.ColgStuFeesViewHolder> {


    Context context;
    List<ColgStuFeesResponseDTO> colgStuFeesResponseDTOList;
    ColgFeesClickListener colgFeesClickListener;


    interface ColgFeesClickListener {
        public void colgFeesClick(String modeOfFees);
    }

    public ColgStuFeesAdapter(Context context, List<ColgStuFeesResponseDTO> colgStuFeesResponseDTOList, ColgFeesClickListener colgFeesClickListener) {
        this.context = context;
        this.colgStuFeesResponseDTOList = colgStuFeesResponseDTOList;
        this.colgFeesClickListener = colgFeesClickListener;
    }

    public void setData(List<ColgStuFeesResponseDTO> colgStuFeesResponseDTOList) {
        this.colgStuFeesResponseDTOList = colgStuFeesResponseDTOList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ColgStuFeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_colg_stu_fees, parent, false);
        return new ColgStuFeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColgStuFeesViewHolder holder, int position) {


        holder.colgHeadDegree.setText(colgStuFeesResponseDTOList.get(position).getColgStuDegree());
        holder.colgHeadHours.setText(colgStuFeesResponseDTOList.get(position).getColgStuHours());
        holder.colgHeadOnlineFees.setText(colgStuFeesResponseDTOList.get(position).getColgStuOnlineFees());
        holder.colgHeadOflineFees.setText(colgStuFeesResponseDTOList.get(position).getColgStuOflineFees());

    }

    @Override
    public int getItemCount() {
        return colgStuFeesResponseDTOList.size();
    }

    class ColgStuFeesViewHolder extends RecyclerView.ViewHolder {

        TextView colgHeadDegree, colgHeadHours, colgHeadOnlineFees, colgHeadOflineFees;

        public ColgStuFeesViewHolder(@NonNull View itemView) {
            super(itemView);

            colgHeadDegree = (TextView) itemView.findViewById(R.id.colgHeadDegree);
            colgHeadHours = (TextView) itemView.findViewById(R.id.colgHeadHours);
            colgHeadOnlineFees = (TextView) itemView.findViewById(R.id.colgHeadOnlineFees);
            colgHeadOflineFees = (TextView) itemView.findViewById(R.id.colgHeadOflineFees);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Please Select Mode Of Class");

                    builder.setMessage("Do you want Online or Ofline Class?");

                    builder.setPositiveButton("ONLINE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ColgStuFeesResponseDTO colgStuFeesResponseDTO = colgStuFeesResponseDTOList.get(getAdapterPosition());
                            colgFeesClickListener.colgFeesClick(colgStuFeesResponseDTO.getColgStuOnlineFees());
                        }
                    });

                    builder.setNegativeButton("OFFLINE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ColgStuFeesResponseDTO colgStuFeesResponseDTO = colgStuFeesResponseDTOList.get(getAdapterPosition());
                            colgFeesClickListener.colgFeesClick(colgStuFeesResponseDTO.getColgStuOflineFees());

                        }
                    });

                    builder.show();

                }


            });

        }
    }

}
