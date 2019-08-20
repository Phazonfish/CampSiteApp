package com.cs360.mattryan.campsiteapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class SitePicScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_pic_screen);

        //path hardcoded due to inconsistencies that occured in testing
        File imgFile = new  File("/sdcard/" + getIntent().getStringExtra("site name") + ".jpg");

        if(imgFile.exists()){//set the image based on the site name passed with intent

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.imageView2);

            myImage.setImageBitmap(myBitmap);

        }
    }


}
