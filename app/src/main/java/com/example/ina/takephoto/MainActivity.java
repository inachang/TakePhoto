package com.example.ina.takephoto;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView group4ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button group4Button = (Button) findViewById(R.id.group4Button);
        group4ImageView = (ImageView) findViewById(R.id.group4ImageView);

        //disable button if user has no camera
        if (!hasCamera())
            group4Button.setEnabled(false);
    }

    //check if user has a camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //launching camera app
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //launch camera
        //take a picture and pass result/image along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //get photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data"); //return photo information and converting to bitmap
            group4ImageView.setImageBitmap(photo);

        }
    }
}
