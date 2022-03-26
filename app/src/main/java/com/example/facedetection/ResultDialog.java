package com.example.facedetection;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facedetection.Adapter.RVAdapter;
import com.example.facedetection.Model.FaceModel;

import java.util.List;

public class ResultDialog extends DialogFragment {
    Button okBtn;
    RecyclerView recyclerView;
    RVAdapter rvAdapter;
    List<FaceModel> faceModels;
    Context context;


    ResultDialog (Context ctx, List<FaceModel> faceModelList)
    {
        faceModels=faceModelList;
        context =ctx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_resultdialog, container, false );

        String resultText="";
        okBtn=view.findViewById( R.id.result_ok_button );
        recyclerView=view.findViewById( R.id.recycler_view);


        ShowData();
        // To get the result text
        // after final face detection
        // and append it to the text view.


        // Onclick listener so as
        // to make a dismissable button

        okBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        } );
        return view;

    }

    private void ShowData(){
        rvAdapter=new RVAdapter( faceModels );
        recyclerView.setAdapter( rvAdapter );
        LinearLayoutManager layoutManager=new LinearLayoutManager( context );
        recyclerView.setLayoutManager( layoutManager );
    }


}
