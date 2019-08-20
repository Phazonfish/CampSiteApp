package com.cs360.mattryan.campsiteapp;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ContactScreen extends AppCompatActivity {
    /**
     * Register your here app https://dev.twitter.com/apps/new and get your
     * consumer key and secret
     * */
    static String TWITTER_CONSUMER_KEY = "LMSL6MKQPQy32h10xaQqYw0Jq";
    static String TWITTER_CONSUMER_SECRET = "sqrlUtBwcEzXH9n8aluhoPaBz4ds8ths5sODGUyUmMf3pQXlhI";

    // Internet Connection detector
    private ConnectionDetector cd;

    private static Twitter twitter;
    private static RequestToken requestToken;
    private static AccessToken accessToken;

    // Preference Constants
    static String PREFERENCE_NAME = "twitter_oauth";
    static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

    static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

    // Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

    // Shared Preferences
    private static SharedPreferences mSharedPreferences;

    private EditText sendTo, pinField, TweetBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_screen);
        sendTo = (EditText) findViewById(R.id.sendTo);
        pinField = (EditText) findViewById(R.id.pinField);
        TweetBox = (EditText) findViewById(R.id.TweetBox);

        //cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        //if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            // stop executing code by return
            //return;
        //}

        // Check if twitter keys are set
        //if(TWITTER_CONSUMER_KEY.trim().length() == 0 || TWITTER_CONSUMER_SECRET.trim().length() == 0){
            // Twitter keys not set
            // stop executing code by return
            //return;
        //}

        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);
        try {
            //requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
            //sendTo.setText(requestToken.getAuthorizationURL());
        } catch (Exception e) {
            sendTo.setText("Request token error: " + e.getMessage());
        }

    }

    public void goToMain(View view){
        finish();
    }

    public void sendTweet(View view){

    }

    private void loginToTwitter() {
        // Check if already logged in
        if (!isTwitterLoggedInAlready()) {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
            Configuration configuration = builder.build();

            TwitterFactory factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();

            try {
                requestToken = twitter
                        .getOAuthRequestToken(TWITTER_CALLBACK_URL);
                this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse(requestToken.getAuthenticationURL())));
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        } else {
            // user already logged into twitter
            Toast.makeText(getApplicationContext(),
                    "Already Logged into twitter", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Check user already logged in your application using twitter Login flag is
     * fetched from Shared Preferences
     * */
    private boolean isTwitterLoggedInAlready() {
        // return twitter login status from Shared Preferences
        return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
    }
}
