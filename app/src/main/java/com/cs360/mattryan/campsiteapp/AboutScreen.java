package com.cs360.mattryan.campsiteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
    }

    public void goToMap(View view){
        Intent intent = new Intent(this, RealMap.class);
        startActivity(intent);
    }

    public void goBack(View view){
        finish();
    }
}
