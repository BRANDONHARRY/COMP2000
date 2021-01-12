package com.Controller;

import com.Model.StockModel;
import com.View.StockView;

public class StockController {
    public StockView view;
    public StockModel model;


    public StockController(StockView view, StockModel model) {
//begin of modifiable zone(JavaCode)......C/540be4da-62f4-44bc-92a5-b461b9f02ce0
        this.model = model;
        this.view = view;
//end of modifiable zone(JavaCode)........E/540be4da-62f4-44bc-92a5-b461b9f02ce0
    }

    public void updateView() {
//begin of modifiable zone(JavaCode)......C/f7854ace-50dc-432f-8c21-bacfc1224a77
        view.printStock(model.getBarcode(), model.getName(), model.getPrice(), model.getStockLevel());
//end of modifiable zone(JavaCode)........E/f7854ace-50dc-432f-8c21-bacfc1224a77
    }
    public void updateStock(){

    }
}
