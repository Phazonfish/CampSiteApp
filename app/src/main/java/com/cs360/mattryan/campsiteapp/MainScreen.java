package com.cs360.mattryan.campsiteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;


import java.net.URL;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Social media integration component uses pieces from the new discontinued Twitter Kit API, as per the selection in the requirements matrix
        //Incomplete components that did not function due to this lack of support can be found in the definition of activities not accessible from
        //the usable set of activities.
        final Button tweetComposer = findViewById(R.id.button13);
        tweetComposer.setOnClickListener(view -> {
            try {
                new TweetComposer.Builder(MainScreen.this)
                        .text("@FbcInbox ")
                        .url(new URL("http://www.twitter.com"))
                        .show();

            } catch (Exception e) {
                //Log.e(TAG, "error creating tweet intent", e);
            }
        });
    }

    public void goToSites(View view){
        Intent intent = new Intent(this, SiteListScreen.class);
        startActivity(intent);
    }

    public void goToRating(View view){
        Intent intent = new Intent(this, RatingScreen.class);
        startActivity(intent);
    }

    public void goToAbout(View view){
        Intent intent = new Intent(this, AboutScreen.class);
        startActivity(intent);
    }

    public void goToStart(View view){
        finish();
    }

}
