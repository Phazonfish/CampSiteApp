package com.cs360.mattryan.campsiteapp;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CampMapScreen extends AppCompatActivity {

    int siteX;
    int siteY;
    Point size;
    ImageView imageView;
    private LinearLayout.LayoutParams layoutParams;

    //This non-google maps component was implemented in addition to the google maps api because it more meaningfully interacts with the database results.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_map_screen);
        imageView = (ImageView) findViewById(R.id.imageView);//This object is the marker indicating the site. The background image is the map

        layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.leftMargin = getIntent().getIntExtra("x value", 0);
        layoutParams.topMargin = getIntent().getIntExtra("y value", 0);
        imageView.setLayoutParams(layoutParams);

    }
}
