package com.cs360.mattryan.campsiteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void goToCreation(View view){
        Intent intent = new Intent(this, AccountCreationScreen.class);
        startActivity(intent);
    }

    public void goToAbout(View view){
        Intent intent = new Intent(this, AboutScreen.class);
        startActivity(intent);
    }
}
