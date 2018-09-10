package com.sthiddov.youareaskilledcook.modils;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Howcook {
    private int id,timecook,timefire,pepole,shop;
    private Boolean checkfrv;
    private String dec,title;
    private Bitmap image;
private ArrayList<String> listshop;
    public Howcook() {
    }

    public Howcook(int id, int timecook, int timefire, int pepole, int shop, Boolean checkfrv, String dec,String title, Bitmap image,ArrayList<String> listshop) {
        this.id = id;
        this.timecook = timecook;
        this.timefire = timefire;
        this.pepole = pepole;
        this.shop = shop;
        this.checkfrv = checkfrv;
        this.dec = dec;
        this.image = image;
this.title=title;
this.listshop=listshop;
    }

    public ArrayList<String> getListshop() {
        return listshop;
    }

    public void setListshop(ArrayList<String> listshop) {
        this.listshop = listshop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimecook() {
        return timecook;
    }

    public void setTimecook(int timecook) {
        this.timecook = timecook;
    }

    public int getTimefire() {
        return timefire;
    }

    public void setTimefire(int timefire) {
        this.timefire = timefire;
    }

    public int getPepole() {
        return pepole;
    }

    public void setPepole(int pepole) {
        this.pepole = pepole;
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    public Boolean getCheckfrv() {
        return checkfrv;
    }

    public void setCheckfrv(Boolean checkfrv) {
        this.checkfrv = checkfrv;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String title) {
        this.dec = dec;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
