package com.example.hawkergogo;

public class CartItem {
    private int imageId;
    private String title;
    private String reserved;
    private String pickup;

    private int qty;

    public CartItem(int imageId, String title, String pickup) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.qty = 0;
    }

    public CartItem(int imageId, String title, String pickup, String reserved) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.reserved = reserved;
        this.qty = 0;
    }

    public CartItem(int imageId, String title, String pickup, int qty) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.qty = qty;
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

    public String getReserved() {
        return reserved;
    }
    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
