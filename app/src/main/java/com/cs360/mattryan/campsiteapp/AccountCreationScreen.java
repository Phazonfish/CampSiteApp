package com.cs360.mattryan.campsiteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AccountCreationScreen extends AppCompatActivity {

    EditText newAccountName, newAccountPass;
    String filename = "CampAppUserInfo";
    String defaultAccount = "User:Phazonfish-vk19fcv1";
    String newAccountStorage;
    File file;
    FileInputStream readStream;
    FileOutputStream writeStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_screen);
        newAccountName = (EditText) findViewById(R.id.newAccountName);
        newAccountPass = (EditText) findViewById(R.id.newAccountPass);

        try {
            readStream = openFileInput(filename);
            readStream.close();
        }
        catch (Exception e) {
            newAccountName.setText("First-time user detected");
            newAccountPass.setText("Please press \"Back\" and try again");
            file = new File(getFilesDir(), filename);
            try {
                writeStream = openFileOutput(filename, MODE_PRIVATE);
                writeStream.write(defaultAccount.getBytes());
                writeStream.close();
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }

    public void addNewAccount(View view) {
        try {
            writeStream = openFileOutput(filename, MODE_PRIVATE);
            if ((newAccountName.getText().toString().length() > 0) && (newAccountPass.getText().toString().length() > 0)) {
                newAccountStorage = " User:" + newAccountName.getText().toString() + "-" + newAccountPass.getText().toString();
                writeStream.write(newAccountStorage.getBytes());
                newAccountName.setText("");
                newAccountPass.setText("");
            } else {
                newAccountName.setText("Please enter both");
                newAccountPass.setText("a username and a password");
            }
            writeStream.close();
        } catch (Exception f) {
            f.printStackTrace();
        }
    }

    public void backToStart(View view){
        finish();
    }
}
