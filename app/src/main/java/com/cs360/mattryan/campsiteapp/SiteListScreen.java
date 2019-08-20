package com.cs360.mattryan.campsiteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class SiteListScreen extends AppCompatActivity {

    EditText siteName, priceField, elecField, waterField, fireField, greyField, blackField, xField, yField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list_screen);
        siteName = (EditText) findViewById(R.id.siteName);//initialize objects that provide input
        priceField = (EditText) findViewById(R.id.priceField);
        elecField = (EditText) findViewById(R.id.elecField);
        waterField = (EditText) findViewById(R.id.waterField);
        fireField = (EditText) findViewById(R.id.fireField);
        greyField = (EditText) findViewById(R.id.greyField);
        blackField = (EditText) findViewById(R.id.blackField);
        xField = (EditText) findViewById(R.id.xField);
        yField = (EditText) findViewById(R.id.yField);
    }

    public void newSite(View view) {
        SiteDBHandler dbHandler = new SiteDBHandler(this, null, null, 1);
        Site site = new Site(siteName.getText().toString(), Integer.parseInt(priceField.getText().toString()),
                SiteDBHandler.toBool(Integer.parseInt(elecField.getText().toString())), SiteDBHandler.toBool(Integer.parseInt(waterField.getText().toString())),
                SiteDBHandler.toBool(Integer.parseInt(fireField.getText().toString())), SiteDBHandler.toBool(Integer.parseInt(greyField.getText().toString())),
                SiteDBHandler.toBool(Integer.parseInt(blackField.getText().toString())), Integer.parseInt(xField.getText().toString()),
                Integer.parseInt(yField.getText().toString()));//create a site from the text fields
        dbHandler.addSite(site);//add site to the database
        siteName.setText("");//clears text fields
        priceField.setText("");
        elecField.setText("");
        waterField.setText("");
        fireField.setText("");
        greyField.setText("");
        blackField.setText("");
        xField.setText("");
        yField.setText("");
    }

    public void searchSite(View view) {
        SiteDBHandler dbHandler = new SiteDBHandler(this, null, null, 1);
        Site site = dbHandler.searchSite(siteName.getText().toString());//search for site based on name
        if (site != null) {//fills text fields with values from found site
            priceField.setText(String.valueOf(site.getPrice()));
            elecField.setText(String.valueOf(SiteDBHandler.toInt(site.hasElec())));
            waterField.setText(String.valueOf(SiteDBHandler.toInt(site.hasWater())));
            fireField.setText(String.valueOf(SiteDBHandler.toInt(site.hasFire())));
            greyField.setText(String.valueOf(SiteDBHandler.toInt(site.hasGrey())));
            blackField.setText(String.valueOf(SiteDBHandler.toInt(site.hasBlack())));
            xField.setText(String.valueOf(site.getX()));
            yField.setText(String.valueOf(site.getY()));
        }
        else {
            siteName.setText("Site not found.");//indicates search failed
            priceField.setText("");
            elecField.setText("");
            waterField.setText("");
            fireField.setText("");
            greyField.setText("");
            blackField.setText("");
            xField.setText("");
            yField.setText("");
        }
    }

    public void deleteSite(View view) {
        SiteDBHandler dbHandler = new SiteDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteSite(siteName.getText().toString());//finds and deletes site based on name
        if (result) {
            siteName.setText("Site Deleted");//indicates successful deletion
            priceField.setText("");//clears text fields
            elecField.setText("");
            waterField.setText("");
            fireField.setText("");
            greyField.setText("");
            blackField.setText("");
            xField.setText("");
            yField.setText("");
        }
        else {
            siteName.setText("Site not found.");//indicates failed search and deletion
            priceField.setText("");//clears text fields
            elecField.setText("");
            waterField.setText("");
            fireField.setText("");
            greyField.setText("");
            blackField.setText("");
            xField.setText("");
            yField.setText("");
        }
    }

    public void updateSite(View view) {
        SiteDBHandler dbHandler = new SiteDBHandler(this, null, null, 1);
        Site site = dbHandler.searchSite(siteName.getText().toString());
        if (site != null) {
            site = new Site(siteName.getText().toString(), Integer.parseInt(priceField.getText().toString()),
                    SiteDBHandler.toBool(Integer.parseInt(elecField.getText().toString())), SiteDBHandler.toBool(Integer.parseInt(waterField.getText().toString())),
                    SiteDBHandler.toBool(Integer.parseInt(fireField.getText().toString())), SiteDBHandler.toBool(Integer.parseInt(greyField.getText().toString())),
                    SiteDBHandler.toBool(Integer.parseInt(blackField.getText().toString())), Integer.parseInt(xField.getText().toString()),
                    Integer.parseInt(yField.getText().toString()));//creates site from text fields
            dbHandler.updateSite(site);//ensures entry of the same name in the database matches new values
        }
        else {
            siteName.setText("Site not found.");//indicates search failure
            priceField.setText("");//clears text fields
            elecField.setText("");
            waterField.setText("");
            fireField.setText("");
            greyField.setText("");
            blackField.setText("");
            xField.setText("");
            yField.setText("");
        }
    }

    public void goToCampMap(View view){//transition to (non-API) map screen, passing values for placing marker
        Intent intent = new Intent(this, CampMapScreen.class);
        if (xField.getText().toString().isEmpty()) {
            intent.putExtra("x value", 0);
        } else {
            intent.putExtra("x value", Integer.valueOf(xField.getText().toString()));
        }
        if (yField.getText().toString().isEmpty()) {
            intent.putExtra("y value", 0);
        } else {
            intent.putExtra("y value", Integer.valueOf(yField.getText().toString()));
        }
        startActivity(intent);
    }

    public void showPic(View view){//transition to screen showing site pic
        Intent intent = new Intent(this, SitePicScreen.class);
        if (siteName.getText().toString().isEmpty()) {//pass the site name to next screen
            intent.putExtra("site name", "");//so it can associate pics w/ sites
        } else {
            intent.putExtra("site name", siteName.getText().toString());
        }
        startActivity(intent);
    }

    public void goToCamera(View view){//transition to screen allowing user to take a picture of the site with the phone's camera
        Intent intent = new Intent(this, CameraScreen.class);
        if (siteName.getText().toString().isEmpty()) {
            intent.putExtra("site name", "");
        } else {
            intent.putExtra("site name", siteName.getText().toString());
        }
        startActivity(intent);
    }

    public void goToMain(View view){
        finish();
    }
}
