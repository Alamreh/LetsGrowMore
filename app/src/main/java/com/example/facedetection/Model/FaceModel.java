package com.example.facedetection.Model;

public class FaceModel {
    int FaceId;
    float AngleX;
    float AngleY;
    float AngleZ;

    float smile;
    float LeftEye;
    float RightEye;

    public FaceModel() {
    }

    public FaceModel(int faceId, float angleX, float angleY, float angleZ, float smile, float leftEye, float rightEye) {
        FaceId = faceId;
        AngleX = angleX;
        AngleY = angleY;
        AngleZ = angleZ;
        this.smile = smile;
        LeftEye = leftEye;
        RightEye = rightEye;
    }

    public int getFaceId() {
        return FaceId;
    }

    public void setFaceId(int faceId) {
        FaceId = faceId;
    }

    public float getAngleX() {
        return AngleX;
    }

    public void setAngleX(float angleX) {
        AngleX = angleX;
    }

    public float getAngleY() {
        return AngleY;
    }

    public void setAngleY(float angleY) {
        AngleY = angleY;
    }

    public float getAngleZ() {
        return AngleZ;
    }

    public void setAngleZ(float angleZ) {
        AngleZ = angleZ;
    }

    public float getSmile() {
        return smile;
    }

    public void setSmile(float smile) {
        this.smile = smile;
    }

    public float getLeftEye() {
        return LeftEye;
    }

    public void setLeftEye(float leftEye) {
        LeftEye = leftEye;
    }

    public float getRightEye() {
        return RightEye;
    }

    public void setRightEye(float rightEye) {
        RightEye = rightEye;
    }
}
