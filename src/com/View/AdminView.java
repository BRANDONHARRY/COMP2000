package com.View;

import com.Controller.KioskController;
import com.Model.StockModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminView {
    public JList adminStockList;
    public JList tempStockList;
    public JPanel mainPanel;
    public JButton logOutBtn;
    public JButton addBtn;
    private JButton editBtn;
    private JButton removeBtn;
    private JButton textBtn;
    public String filePath = "resources\\stock.txt";
    public String separator = "\\|";
    public JOptionPane popUp;

    public AdminView(JFrame adminFrame, JList stockList) {
        adminStockList.setModel(new DefaultListModel());

        loadFile();

        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KioskController.openKiosk(adminFrame);
            }
        });
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSave();
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        textBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
    }
    private void loadFile(){
        StockModel newStock = new StockModel();
        newStock.load();
        StockModel[] tempArray = new StockModel[0];
        tempArray = newStock.stock.toArray(tempArray);

        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < tempArray.length; i++){

            listModel.addElement(tempArray[i].getBarcode() +" | " + tempArray[i].getName() + " | " + tempArray[i].getPrice() + " | " + tempArray[i].getStockLevel());
        }
        adminStockList.setModel(listModel);
    }
    private void openFile(){
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openSave(){
        JFrame popUpFrame = new JFrame();
        popUpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JTextField barcodeTF = new JTextField();
        JTextField nameTF = new JTextField();
        JTextField priceTF = new JTextField();
        JTextField stockLevelTF = new JTextField();

        Object[] message = {
                "Barcode: ", barcodeTF,
                "Product name: ", nameTF,
                "Price: £", priceTF,
                "Stock level: ", stockLevelTF
        };

        int option = popUp.showConfirmDialog(popUpFrame, message, "Add", JOptionPane.INFORMATION_MESSAGE);

        if(option == popUp.OK_OPTION){
            if(barcodeTF != null && nameTF != null && priceTF != null && stockLevelTF != null){
                saveFile(barcodeTF.getText(), nameTF.getText(), priceTF.getText(), stockLevelTF.getText());
            }
            else{
                popUp.showMessageDialog(popUpFrame, "Please enter details into all fields.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }


    }
    public void saveFile(String barcode, String name, String price, String stockLevel){
        String barcodeSave = barcode;
        String nameSave = name;
        String priceSave = price;
        String stockLevelSave = stockLevel;

        StockModel newStock = new StockModel();
        newStock.load();
        StockModel[] tempArray = new StockModel[0];
        tempArray = newStock.stock.toArray(tempArray);

        newStock.setBarcode(barcodeSave);
        newStock.setName(nameSave);
        newStock.setPrice(priceSave);
        newStock.setStockLevel(Integer.parseInt(stockLevel));


        try{
            FileWriter writer = new FileWriter(filePath);

            for(int i = 0; i < tempArray.length; i++){
                String data = "";

                if (i > 0){
                    data += "\n";
                }

                String barcodeTemp = newStock.getBarcode();
                data += barcodeTemp + "|";

                String nameTemp = newStock.getName();
                data += nameTemp + "|";

                String priceTemp = newStock.getPrice();
                data += priceTemp + "|";

                String stockLevelTemp = newStock.getStockLevel().toString();
                data += stockLevelTemp + "|";

                writer.write(data);
            }
            writer.close();
            System.out.println("New product added");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}