package com.sthiddov.youareaskilledcook.modils;

public class Crtag {
private int id,idimage;
private String title;

    public Crtag() {
    }

    public Crtag(int id, int idimage, String title) {
        this.id = id;
        this.idimage = idimage;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        this.idimage = idimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
