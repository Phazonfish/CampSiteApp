package com.cs360.mattryan.campsiteapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.ContentObservable;
import android.database.Cursor;

public class SiteDBHandler extends SQLiteOpenHelper {
    private static final int DB_VER = 1;
    private static final String DB_NAME = "campDB.db";

    public static final String TABLE_SITES = "sites";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_ELEC = "elec";
    public static final String COLUMN_WATER = "water";
    public static final String COLUMN_FIRE = "fire";
    public static final String COLUMN_GREY = "grey";
    public static final String COLUMN_BLACK = "black";
    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";

    public SiteDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SITES_TABLE = "CREATE TABLE " + TABLE_SITES + " (" + COLUMN_NAME + " TEXT PRIMARY KEY, " + COLUMN_PRICE + " INTEGER, " + COLUMN_ELEC
                + " INTEGER, " + COLUMN_WATER + " INTEGER, " + COLUMN_FIRE + " INTEGER, " + COLUMN_GREY + " INTEGER, " + COLUMN_BLACK + " INTEGER, " +
                COLUMN_X + " INTEGER, " + COLUMN_Y + " INTEGER)";
        db.execSQL(CREATE_SITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITES);
        onCreate(db);
    }

    public void addSite(Site site){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, site.getName());
        values.put(COLUMN_PRICE, site.getPrice());
        values.put(COLUMN_ELEC, toInt(site.hasElec()));
        values.put(COLUMN_WATER, toInt(site.hasWater()));
        values.put(COLUMN_FIRE, toInt(site.hasFire()));
        values.put(COLUMN_GREY, toInt(site.hasGrey()));
        values.put(COLUMN_BLACK, toInt(site.hasBlack()));
        values.put(COLUMN_X, site.getX());
        values.put(COLUMN_Y, site.getY());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SITES, null, values);
        db.close();
    }

    public Site searchSite(String siteName){
        String query = "SELECT * FROM " + TABLE_SITES + " WHERE " + COLUMN_NAME + " = \"" + siteName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Site site = new Site();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            site.setName(cursor.getString(0));
            site.setPrice(Integer.parseInt(cursor.getString(1)));
            site.setElec(toBool(Integer.parseInt(cursor.getString(2))));
            site.setWater(toBool(Integer.parseInt(cursor.getString(3))));
            site.setFire(toBool(Integer.parseInt(cursor.getString(4))));
            site.setGrey(toBool(Integer.parseInt(cursor.getString(5))));
            site.setBlack(toBool(Integer.parseInt(cursor.getString(6))));
            site.setX(Integer.parseInt(cursor.getString(7)));
            site.setY(Integer.parseInt(cursor.getString(8)));
            cursor.close();
        }
        else {
            site = null;
        }
        db.close();
        return site;
    }

    public boolean deleteSite(String siteName){
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_SITES + " WHERE " + COLUMN_NAME + " = \"" + siteName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Site site = new Site();
        if (cursor.moveToFirst()) {
            site.setName(cursor.getString(0));
            db.delete(TABLE_SITES, COLUMN_NAME + " = ?", new String[] { site.getName()});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void updateSite(Site site){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, site.getName());
        values.put(COLUMN_PRICE, site.getPrice());
        values.put(COLUMN_ELEC, toInt(site.hasElec()));
        values.put(COLUMN_WATER, toInt(site.hasWater()));
        values.put(COLUMN_FIRE, toInt(site.hasFire()));
        values.put(COLUMN_GREY, toInt(site.hasGrey()));
        values.put(COLUMN_BLACK, toInt(site.hasBlack()));
        values.put(COLUMN_X, site.getX());
        values.put(COLUMN_Y, site.getY());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_SITES, values, COLUMN_NAME + " = ?", new String[] { site.getName()});
    }

    public static int toInt(boolean value) {
        if (value) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public static boolean toBool(int value) {
        if (value == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
