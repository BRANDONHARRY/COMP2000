package com.Model;


public class StockModel {
    public String barcode;
    public String name;
    public String price;

    public String getBarcode(){
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
