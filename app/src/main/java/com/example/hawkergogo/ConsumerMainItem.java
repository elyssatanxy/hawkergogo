package com.example.hawkergogo;

public class ConsumerMainItem {
    private int imageId;
    private String title;
    private String timing;

    public ConsumerMainItem(int imageId, String title, String timing) {
        this.imageId = imageId;
        this.title = title;
        this.timing = timing;
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
