package com.View;

import com.Controller.KioskController;
import com.Model.StockModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AdminView {
    public JList adminStockList;
    public JList tempStockList;
    public JPanel mainPanel;
    public JButton logOutBtn;
    public JButton addBtn;
    private JButton editBtn;
    private JButton removeBtn;
    private JButton openBtn;
    private JButton addAdminBtn;
    private JButton updateBtn;
    public String filePath = "resources\\stock.txt";
    public String separator = "\\|";
    public JOptionPane popUp;
    public JFrame popUpFrame = new JFrame();


    public AdminView(JFrame adminFrame, JList stockList) {
        adminStockList.setModel(new DefaultListModel());
        popUpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
                addProductPopup();
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProductPopup();
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProductPopup();
            }
        });
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
                textUpdate();
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
            System.out.println("File Opened");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addProductPopup(){
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
                addProduct(barcodeTF.getText(), nameTF.getText(), priceTF.getText(), stockLevelTF.getText());
                loadFile();
                System.out.println("New product added");
            }
            else{
                popUp.showMessageDialog(popUpFrame, "Please enter details into all fields.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            popUp.showMessageDialog(popUpFrame, "No product added" , "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void addProduct(String barcode, String name, String price, String stockLevel){
        StockModel newProduct = new StockModel();
        newProduct.load();

        newProduct.setBarcode(barcode);
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setStockLevel(Integer.parseInt(stockLevel));

        newProduct.addProduct(newProduct);
        saveFile(newProduct);
    }
    public void saveFile(StockModel tempProduct){
        ArrayList<StockModel> tempStock = new ArrayList<StockModel>();
        tempStock = tempProduct.stock;

        try{
            FileWriter writer = new FileWriter(filePath);

            for(int i = 0; i < tempStock.size(); i++){
                String data = "";

                if (i > 0){
                    data += "\n";
                }

                String barcodeTemp = tempStock.get(i).getBarcode();
                data += barcodeTemp + "|";

                String nameTemp = tempStock.get(i).getName();
                data += nameTemp + "|";

                String priceTemp = tempStock.get(i).getPrice();
                data += priceTemp + "|";

                String stockLevelTemp = tempStock.get(i).getStockLevel().toString();
                data += stockLevelTemp + "|";

                writer.write(data);
            }
            writer.close();
            System.out.println("Saved to file");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void editProductPopup(){
        int index = adminStockList.getSelectedIndex();

        StockModel loadStock = new StockModel();
        loadStock.load();

        StockModel tempNames = loadStock.getProductAt(index);

        JTextField barcodeTF = new JTextField();
        JTextField nameTF = new JTextField();
        JTextField priceTF = new JTextField();
        JTextField stockLevelTF = new JTextField();

        barcodeTF.setText(tempNames.getBarcode());
        nameTF.setText(tempNames.getName());
        priceTF.setText(tempNames.getPrice());
        stockLevelTF.setText(tempNames.getStockLevel().toString());

        Object[] message = {
                "Barcode: ", barcodeTF,
                "Product name: ", nameTF,
                "Price: £", priceTF,
                "Stock level: ", stockLevelTF
        };

        int option = popUp.showConfirmDialog(popUpFrame, message, "Edit", JOptionPane.INFORMATION_MESSAGE);

        if(option == popUp.OK_OPTION){
            if(barcodeTF != null && nameTF != null && priceTF != null && stockLevelTF != null){
                editProduct(barcodeTF.getText(), nameTF.getText(), priceTF.getText(), stockLevelTF.getText());
                popUp.showMessageDialog(popUpFrame, "Edit Accepted", "Edit", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Edit accepted");
                loadFile();
            }
            else{
                popUp.showMessageDialog(popUpFrame, "Please enter details into all fields.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            popUp.showMessageDialog(popUpFrame, "No product edited" , "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void removeProductPopup(){
        int index = adminStockList.getSelectedIndex();

        StockModel loadStock = new StockModel();
        loadStock.load();

        StockModel tempNames = loadStock.getProductAt(index);

        JLabel barcodeLbl = new JLabel();
        JLabel nameLbl = new JLabel();
        JLabel priceLbl = new JLabel();
        JLabel stockLevelLbl = new JLabel();

        barcodeLbl.setText(tempNames.getBarcode());
        nameLbl.setText(tempNames.getName());
        priceLbl.setText(tempNames.getPrice());
        stockLevelLbl.setText(tempNames.getStockLevel().toString());

        Object[] message = {
                "Are you sure you want to remove this product: ",
                "Barcode: ", barcodeLbl,
                "Product name: ", nameLbl,
                "Price: £", priceLbl,
                "Stock level: ", stockLevelLbl
        };

        int option = popUp.showConfirmDialog(popUpFrame, message, "Delete", JOptionPane.INFORMATION_MESSAGE);

        if(option == popUp.OK_OPTION){
            deleteProduct();
            popUp.showMessageDialog(popUpFrame, "Deletion Completed", "Accepted", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Deleted item");
            loadFile();
        }
        else{
            popUp.showMessageDialog(popUpFrame, "Deletion Canceled", "Deletion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void textUpdate(){
        int option = popUp.showConfirmDialog(popUpFrame, "Update List", "Update", JOptionPane.INFORMATION_MESSAGE);
        if(option == popUp.OK_OPTION){
            loadFile();
        }
        else{
            loadFile();
        }
    }
    public void editProduct(String barcode, String name, String price, String stockLevel){
        StockModel loadStock = new StockModel();
        loadStock.load();

        StockModel editStock = loadStock.getProductAt(adminStockList.getSelectedIndex());


        editStock.setBarcode(barcode);
        editStock.setName(name);
        editStock.setPrice(price);
        editStock.setStockLevel(Integer.parseInt(stockLevel));

        saveFile(loadStock);
    }
    public void deleteProduct(){
        StockModel loadStock = new StockModel();
        loadStock.load();

        StockModel deleteStock = loadStock.getProductAt(adminStockList.getSelectedIndex());

        loadStock.removeProduct(deleteStock);

        saveFile(loadStock);
    }
}
