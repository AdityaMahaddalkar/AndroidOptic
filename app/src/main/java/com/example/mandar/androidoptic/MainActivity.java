package com.example.mandar.androidoptic;

import android.content.Context;
import android.content.pm.PackageManager;
//import android.graphics.Camera;
import android.hardware.camera2.*;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private boolean checkCameraHardware(Context context)
    {
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            return true;
        }
        else
        {
            return false;

        }
    }
    public static Camera getCameraInstance()
    {
        Camera c = null;
        try
        {
            c = Camera.open();
        }
        catch(Exception e)
        {

        }
        return c;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean f = checkCameraHardware(this);
        if(!f)
        {
            //Handle True
        }
        else
        {
            //Handle False
            //Assuming every hardware has been provided with Camera
        }

        mCamera = getCameraInstance();
        mPreview=  new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout)findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    protected void onPause(){
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
}
