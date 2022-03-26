package com.example.facedetection.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facedetection.Model.FaceModel;
import com.example.facedetection.R;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

    List<FaceModel> faceList;

    public RVAdapter(List<FaceModel> fList ) {
        faceList=fList;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout. result_dialog,parent,false);
        RVViewHolder holder = new RVViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        FaceModel data = faceList.get(position);
        holder.FaceID.setText(Integer.toString(data.getFaceId()));
        holder.XRotView.setText(Float.toString(data.getAngleX()));
        holder.YRotView.setText(Float.toString(data.getAngleY()));
        holder.ZRotView.setText(Float.toString(data.getAngleZ()));
        holder.SmileView.setText(Float.toString(data.getSmile()));
        holder.SmileView.setText(Float.toString(data.getSmile()));
        holder.LEyeView.setText(Float.toString(data.getLeftEye()));
        holder.REyeView.setText(Float.toString(data.getRightEye()));

    }

    @Override
    public int getItemCount() {
        if(faceList==null)
            faceList=new ArrayList<>();
        return faceList.size();
    }

    class RVViewHolder extends RecyclerView.ViewHolder{
        TextView FaceID;
        TextView XRotView;
        TextView YRotView;
        TextView ZRotView;
        TextView SmileView;
        TextView LEyeView;
        TextView REyeView;


        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            FaceID = itemView.findViewById( R.id.fid_TV);
            XRotView = itemView.findViewById(R.id.xrot_TV);
            YRotView = itemView.findViewById(R.id.yrot_TV);
            ZRotView = itemView.findViewById(R.id.zrot_TV);
            SmileView = itemView.findViewById(R.id.smile_TV);
            LEyeView = itemView.findViewById(R.id.leye_TV);
            REyeView = itemView.findViewById(R.id.reye_TV);
        }
    }
}
