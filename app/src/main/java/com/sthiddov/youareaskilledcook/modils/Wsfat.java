package com.sthiddov.youareaskilledcook.modils;

import android.graphics.Bitmap;

public class Wsfat {
    private int id;
    private Bitmap imagewsf;
    private String title,time;

    public Wsfat() {
    }

    public Wsfat(Bitmap idimage, String title, String time) {
        this.id = id;
        this.imagewsf = idimage;
        this.title = title;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public Bitmap getImagewsf() {
        return imagewsf;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImagewsf(Bitmap imagewsf) {
        this.imagewsf = imagewsf;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
