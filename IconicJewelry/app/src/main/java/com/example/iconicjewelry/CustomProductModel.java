package com.example.iconicjewelry;

public class CustomProductModel {

    String key = "";
    String productImage = "";
    String productName = "";
    String productPrice = "";

    public CustomProductModel() {
    }

    public CustomProductModel(String key, String productImage, String productName, String productPrice) {
        this.key = key;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

}
