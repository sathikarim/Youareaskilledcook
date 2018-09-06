package com.sthiddov.youareaskilledcook.modils;

public class Wsfat {
    private int id,idimage;
    private String title,time;

    public Wsfat() {
    }

    public Wsfat(int id, int idimage, String title, String time) {
        this.id = id;
        this.idimage = idimage;
        this.title = title;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getIdimage() {
        return idimage;
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

    public void setIdimage(int idimage) {
        this.idimage = idimage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
