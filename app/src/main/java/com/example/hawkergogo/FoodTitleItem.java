package com.example.hawkergogo;

public class FoodTitleItem {
    private String title;
    private int imageId;

    public FoodTitleItem(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
