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

//    public void setBarcode(String barcode) {
////begin of modifiable zone(JavaCode)......C/6813a246-372f-484c-a3a9-bc97bb62a224
//        model.setBarcode(barcode);
////end of modifiable zone(JavaCode)........E/6813a246-372f-484c-a3a9-bc97bb62a224
//    }
//
//    public String getBarcode() {
////begin of modifiable zone(JavaCode)......C/b914c9d1-7f8b-4a0a-aec6-02eadb106193
//        return model.getBarcode();
////end of modifiable zone(JavaCode)........E/b914c9d1-7f8b-4a0a-aec6-02eadb106193
//    }
//
//    public void setName(String name) {
////begin of modifiable zone(JavaCode)......C/e83b400a-4f5e-48e9-9f72-893b16e9422e
//        model.setName(name);
////end of modifiable zone(JavaCode)........E/e83b400a-4f5e-48e9-9f72-893b16e9422e
//    }
//
//    public String getName() {
////begin of modifiable zone(JavaCode)......C/90746ff0-b94e-467a-ae4b-a14166bc08f4
//        return model.getName();
////end of modifiable zone(JavaCode)........E/90746ff0-b94e-467a-ae4b-a14166bc08f4
//    }
//
//    public void setPrice(String price) {
////begin of modifiable zone(JavaCode)......C/4a915b50-eba1-4bed-94c0-cbb45493efd6
//        model.setPrice(price);
////end of modifiable zone(JavaCode)........E/4a915b50-eba1-4bed-94c0-cbb45493efd6
//    }
//
//    public String getPrice() {
////begin of modifiable zone(JavaCode)......C/c5149649-80a6-474c-960d-c063decc3272
//        return model.getPrice();
////end of modifiable zone(JavaCode)........E/c5149649-80a6-474c-960d-c063decc3272
//    }

    public void updateView() {
//begin of modifiable zone(JavaCode)......C/f7854ace-50dc-432f-8c21-bacfc1224a77
        view.printStock(model.getBarcode(), model.getName(), model.getPrice());
//end of modifiable zone(JavaCode)........E/f7854ace-50dc-432f-8c21-bacfc1224a77
    }
}
