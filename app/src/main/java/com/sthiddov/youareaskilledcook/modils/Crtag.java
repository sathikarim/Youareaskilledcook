package com.sthiddov.youareaskilledcook.modils;

import android.graphics.Bitmap;

public class Crtag {
private int id;
    private Bitmap image;

private String title;

    public Crtag() {
    }

    public Crtag(int id, Bitmap image ,String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return image;
    }

    public void setBitmap(Bitmap image) {
        this.image= image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
