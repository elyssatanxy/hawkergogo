package com.example.hawkergogo;

public class CartItem {
    private int imageId;
    private String title;
    private String pickup;

    public CartItem(int imageId, String title, String pickup) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }
}