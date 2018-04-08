package com.awn.app.bingkainusantara.data;

import java.io.Serializable;

/**
 * Created by adewijanugraha on 31/03/18.
 */

public class KulinerItems implements Serializable {
    private int id;
    private String title;
    private String picture;
    private String date;
    private String description;
    private String author;

    public KulinerItems(){

    }

    public KulinerItems(int id, String title, String picture, String date, String description, String author) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.date = date;
        this.description = description;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
