package com.shashi.recyclerviewdemo;

class ProductsData {

    private int image;  //to use id of drawable image
    private String itemName;
    private double itemPrice;

    public ProductsData(int image, String itemName, double itemPrice) {
        this.image = image;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public int getImage() {
        return image;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
