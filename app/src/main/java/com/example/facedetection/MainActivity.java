package com.example.facedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.FaceDetector;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.facedetection.Model.FaceModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button camera_btn;

    InputImage inputImage;

    com.google.mlkit.vision.face.FaceDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        FirebaseApp.initializeApp( this );

        camera_btn=findViewById( R.id.open_camera_btn );

//
        camera_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent,   100);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText( MainActivity.this,"Error:"+e.getMessage() ,Toast.LENGTH_SHORT).show();
                }


            }
        } );
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode ==  100 ) {

            // BitMap is data structure of image file
            // which store the image in memory


            Bitmap bitmap=(Bitmap)  data.getExtras().get( "data" );
            face_detect(bitmap);

        }
    }

    private void face_detect(Bitmap bitmap) {
        // High-accuracy landmark detection and face classification
        FaceDetectorOptions options
                = new FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build();

        try {

            inputImage = InputImage.fromBitmap(bitmap,0);
              detector = FaceDetection.getClient(options);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // [START get_detector]
//
        Task<List<Face>> result=

                detector.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<Face>>()
                {
                    @Override
                    public void onSuccess(List<Face> faces){

                        List<FaceModel> faceModelList=new ArrayList<>();
                        int i = 1;
                        for (Face face :  faces) {
                            FaceModel faceModel = new FaceModel();
                            faceModel.setFaceId(i);
                            faceModel.setAngleX(face.getHeadEulerAngleX());
                            faceModel.setAngleY(face.getHeadEulerAngleY());
                            faceModel.setAngleZ(face.getHeadEulerAngleZ());
                            faceModel.setSmile(face.getSmilingProbability()*100);
                            faceModel.setLeftEye(face.getLeftEyeOpenProbability()*100);
                            faceModel.setRightEye(face.getRightEyeOpenProbability()*100);
                            faceModelList.add(faceModel);
                            i++;


                        }if (faces.size() == 0) {
                            Toast.makeText(MainActivity.this,"NO FACES DETECTED",Toast.LENGTH_SHORT).show();
                        }else {
                           // System.out.println("Done");
                              DialogFragment resultDialog
                                    = new ResultDialog( MainActivity.this,faceModelList);
                            resultDialog.setCancelable(true);
                            resultDialog.show(
                                    getSupportFragmentManager(),
                                    LcoFaceDetection.Result_Dialog);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT ).show();
                    }
                });



    }
}