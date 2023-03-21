package com.example.hawkergogo;

import java.io.Serializable;
import java.util.Date;

public class Lisiting implements Serializable {
    int image;
    String title;
    int portions;
    String location;
    String description;
    String time;

    public Lisiting(int image, String title, int portions, String location, String description, String time) {
        this.image = image;
        this.title = title;
        this.portions = portions;
        this.location = location;
        this.description = description;
        this.time = time;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
