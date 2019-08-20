package com.cs360.mattryan.campsiteapp;

public class Site {
    private String site_name;
    private int site_price, site_x, site_y;
    private boolean site_elec, site_water, site_fire, site_grey, site_black;

    public Site() {
        site_name = "";
        site_price = 0;
        site_elec = false;
        site_water = false;
        site_fire = false;
        site_grey = false;
        site_black = false;
        site_x = 0;
        site_y = 0;
    }

    public Site(String name, int price, boolean elec, boolean water, boolean fire, boolean grey, boolean black, int x, int y) {
        site_name = name;
        site_price = price;
        site_elec = elec;
        site_water = water;
        site_fire = fire;
        site_grey = grey;
        site_black = black;
        site_x = x;
        site_y = y;
    }

    public String getName() {
        return site_name;
    }
    public void setName(String newName) {
        site_name = newName;
    }
    public int getPrice() {
        return site_price;
    }
    public void setPrice(int newPrice) {
        site_price = newPrice;
    }
    public boolean hasElec() {
        return site_elec;
    }
    public void setElec(boolean value) {
        site_elec = value;
    }
    public boolean hasWater() {
        return site_water;
    }
    public void setWater(boolean value) {
        site_water = value;
    }
    public boolean hasFire() {
        return site_fire;
    }
    public void setFire(boolean value) {
        site_fire = value;
    }
    public boolean hasGrey() {
        return site_grey;
    }
    public void setGrey(boolean value) {
        site_grey = value;
    }
    public boolean hasBlack() {
        return site_black;
    }
    public void setBlack(boolean value) {
        site_black = value;
    }
    public int getX() {
        return site_x;
    }
    public void setX(int value) {
        site_x = value;
    }
    public int getY() {
        return site_y;
    }
    public void setY(int value) {
        site_y = value;
    }
}
