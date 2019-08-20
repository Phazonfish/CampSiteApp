package com.cs360.mattryan.campsiteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LoginScreen extends AppCompatActivity {

    EditText UsernameField, PasswordField;
    String filename = "CampAppUserInfo";
    String defaultAccount = "User:Phazonfish-vk19fcv1";
    String allReads = "";
    byte[] storageData;
    File file;
    FileInputStream readStream;
    FileOutputStream writeStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        UsernameField = (EditText) findViewById(R.id.UsernameField);
        PasswordField = (EditText) findViewById(R.id.PasswordField);

        try {
            readStream = openFileInput(filename);
            storageData = new byte[readStream.available()];
            readStream.read(storageData);
            allReads += new String(storageData);
            readStream.close();
        }
        catch (Exception e) {
            UsernameField.setText("First-time user detected");
            PasswordField.setText("Please press \"Back\" and try again");
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

    public void goToMain(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    public void Login(View view) {
        String processing = checkUsername(allReads, UsernameField.getText().toString());
        if (processing.equals("User not found.")) {
            UsernameField.setText(processing);
            PasswordField.setText("");
        } else if (PasswordField.getText().toString().equalsIgnoreCase(processing)) {
            goToMain();
        } else {
            UsernameField.setText("Invalid password.");
            PasswordField.setText("");
        }
    }

    public void backToStart(View view){
        finish();
    }

    private static String checkUsername(String checkFrom, String username) {
        String result = "User not found.";
        int nameLocation = checkFrom.indexOf("User:" + username + "-");
        if(nameLocation != -1) {
            int passEnd = checkFrom.indexOf(" ", (nameLocation + username.length() + 6));
            if (passEnd == -1) {
                passEnd = checkFrom.length();
            }
            result = checkFrom.substring((nameLocation + username.length() + 6), passEnd);
        }
        return result;
    }
}
