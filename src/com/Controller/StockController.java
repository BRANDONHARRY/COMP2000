package com.Controller;

import com.Model.StockModel;
import com.View.StockView;


public class StockController {
    private StockModel model;
    private StockView view;

    public StockController(StockModel model, StockView view){
        this.model = model;
        this.view = view;
    }

    //Barcode setters & getters
    public void setBarcode(String barcode){
        model.setBarcode(barcode);
    }

    public String getBarcode(){
        return model.getBarcode();
    }

    //Name setters & getters
    public void setName(String name){
        model.setName(name);
    }

    public String getName(){
        return model.getName();
    }

    //Price setters & getters
    public void setPrice(String price) {
        model.setPrice(price);
    }

    public String getPrice(){
        return model.getPrice();
    }

    //Update view
    public void updateView(){
        view.printStock(model.getBarcode(), model.getName(), model.getPrice());
    }
}
