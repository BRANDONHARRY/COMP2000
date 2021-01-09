package com.Model;

import  com.View.KioskView;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class StockModel {
    public String barcode;
    public String name;
    public String price;
    public String[] stockData;
    public final ArrayList<StockModel> stock = new ArrayList<StockModel>();

    public String getBarcode() {
//begin of modifiable zone(JavaCode)......C/798b33b8-c813-48cd-9104-e7e706278d11
        System.out.println(barcode);
        return barcode;
//end of modifiable zone(JavaCode)........E/798b33b8-c813-48cd-9104-e7e706278d11
    }

    public String getName() {
//begin of modifiable zone(JavaCode)......C/1b669393-22fc-449b-824c-6230b26724c4
        System.out.println(name);
        return name;
//end of modifiable zone(JavaCode)........E/1b669393-22fc-449b-824c-6230b26724c4
    }

    public String  getPrice() {
//begin of modifiable zone(JavaCode)......C/48f6b2bc-f722-419c-8a3c-31740613dbdb
        System.out.println(price);
        return  price;
//end of modifiable zone(JavaCode)........E/48f6b2bc-f722-419c-8a3c-31740613dbdb
    }


    public void setBarcode(String barcode) {
//begin of modifiable zone(JavaCode)......C/f12bc700-f207-4313-aaf8-7286ae90332a
//        barcode = stockData[0];
        this.barcode = barcode;
//end of modifiable zone(JavaCode)........E/f12bc700-f207-4313-aaf8-7286ae90332a
    }

    public void setName(String name) {
//begin of modifiable zone(JavaCode)......C/c3c907fe-36f9-4de0-aa96-a30283ab52e4
//        name = stockData[1];
        this.name = name;
//end of modifiable zone(JavaCode)........E/c3c907fe-36f9-4de0-aa96-a30283ab52e4
    }

    public void setPrice(String price) {
//begin of modifiable zone(JavaCode)......C/70a7a3c8-ce12-4239-9998-fb579837dcb5
//        price = stockData[2];
        this.price = price;
//end of modifiable zone(JavaCode)........E/70a7a3c8-ce12-4239-9998-fb579837dcb5
    }
    public String[] getStockData(){
        System.out.println(Arrays.toString(stockData));
        return stockData;
    }


//begin of modifiable zone(JavaCode)......C/669615c8-1d1e-498a-96c7-3da088973940

    public void load(){
        String filePath = "resources\\stock.txt";
        String separator = "\\|";

        try{
            File file = new File(filePath);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();

                stockData = data.split(separator);

                StockModel model = new StockModel();

                model.setBarcode(stockData[0]);
                model.setName(stockData[1]);
                model.setPrice(stockData[2]);

                stock.add(model);
                getStockData();
            }
            scanner.close();
            System.out.println("File successfully loaded");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
//end of modifiable zone(JavaCode)........E/669615c8-1d1e-498a-96c7-3da088973940
}

