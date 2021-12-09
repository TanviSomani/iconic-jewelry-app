package com.example.iconicjewelry;

public class CustomizedProductModel {

    String key = "";
    String baseProd = "";
    String currentSourceImage = "";
    String productName = "";
    String productPrice = "";

    public CustomizedProductModel() {
    }

    public CustomizedProductModel(String key, String baseProd, String currentSourceImage, String productName, String productPrice) {
        this.key = key;
        this.baseProd = baseProd;
        this.currentSourceImage = currentSourceImage;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseProd() {
        return baseProd;
    }

    public void setBaseProd(String baseProd) {
        this.baseProd = baseProd;
    }

    public String getCurrentSourceImage() {
        return currentSourceImage;
    }

    public void setCurrentSourceImage(String currentSourceImage) {
        this.currentSourceImage = currentSourceImage;
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
