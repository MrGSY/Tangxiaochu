package com.example.asus.tangtang;

public class FoodListItem {
    private String name;
    private String info;
    private int imageId;

    public FoodListItem(String name, String info, int imageId){
        this.name=name;
        this.info=info;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getImageId() {
        return imageId;
    }
}
