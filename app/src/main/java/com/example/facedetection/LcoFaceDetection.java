package com.example.facedetection;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class LcoFaceDetection extends Application {
  //public static String Result_Text="Result Text";
    public static String  Result_Dialog="Result Dialog";

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp( this );
    }
}

