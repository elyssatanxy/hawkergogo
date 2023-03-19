package com.example.hawkergogo;

import java.util.Date;

public class Lisiting {
    String title;
    int portions;
    String location;
    String description;
    Date time;

    public Lisiting(String title, int portions, String location, String description, Date time) {
        this.title = title;
        this.portions = portions;
        this.location = location;
        this.description = description;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
