package com.example.company;

import android.media.Image;

public class item {
    Image img;
    String ItemName;
    int ItemPrice;

    public item(Image img, String itemName, int itemPrice) {
        this.img = img;
        ItemName = itemName;
        ItemPrice = itemPrice;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        ItemPrice = itemPrice;
    }
}
