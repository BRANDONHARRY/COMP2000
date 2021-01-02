package com.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public class dataManger{
        public String filePath = "resources\\stock.txt";
        public String separator = "\\|";

        public final ArrayList<StockModel> stock = new ArrayList<>();

        public void load(){
            try{
                File file = new File(filePath);

                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String data = scanner.nextLine();

                    String[] stockData = data.split(separator);
                    System.out.println(stockData.length);

//                    StockModel stock = new StockModel();
//
//                    stock.setBarcode(stockData[0]);
//                    stock.setName(stockData[1]);
//                    stock.setPrice(stockData[2]);
                }
                scanner.close();
                System.out.println("File successfully loaded");
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}


