package com.example.hawkergogo;

public class CartItem {
    private String imageId;
    private String title;
    private String reserved;
    private String pickup;

    private int qty;
    private int count;

    public CartItem(String imageId, String title, String pickup) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.qty = 0;
    }

    public CartItem(String imageId, String title, String pickup, String reserved) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.reserved = reserved;
        this.qty = 0;
    }

    public CartItem(String imageId, String title, String pickup, int qty) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.qty = qty;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
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
