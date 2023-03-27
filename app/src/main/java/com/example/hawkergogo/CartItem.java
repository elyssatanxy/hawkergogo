package com.example.hawkergogo;

public class CartItem {

    private int id;
    private String imageId;
    private String title;
    private String reserved;
    private String pickup;

    private int qty;
    private int count;
    private String desc;

    private String location;

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

    public CartItem(String imageId, String title, String pickup, int qty, String desc, String location) {
        this.imageId = imageId;
        this.title = title;
        this.pickup = pickup;
        this.qty = qty;
        this.desc = desc;
        this.location = location;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
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

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
