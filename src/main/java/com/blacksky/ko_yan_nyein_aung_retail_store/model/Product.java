package com.blacksky.ko_yan_nyein_aung_retail_store.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private String name;
    private String category;
//    private Date date;
    private String stringDate;
    private String stringTime;
    private String retailPrice;
    private String wholesalePrice;
    private int productQuantity;

    public Product( String name , String category , String stringDate , String stringTime , String retailPrice , String wholesalePrice , int productQuantity ) {
        this.name = name;
        this.category = category;
        this.stringDate = stringDate;
        this.stringTime = stringTime;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
        this.productQuantity = productQuantity;
    }
    public Product() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public void setStringTime(String stringTime) { this.stringTime = stringTime; }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getStringDate() {
        return stringDate;
    }

    public String getStringTime() { return stringTime; }

    public String getRetailPrice() {
        return retailPrice;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }
}
