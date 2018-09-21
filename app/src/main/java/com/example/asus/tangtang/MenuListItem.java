package com.example.asus.tangtang;

public class MenuListItem {
    private String item_name;
    private int item_image;
    public MenuListItem(String item_name, int item_image){
        this.item_image=item_image;
        this.item_name=item_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public int getItem_image() {
        return item_image;
    }
}
