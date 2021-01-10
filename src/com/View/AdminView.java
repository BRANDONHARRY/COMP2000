package com.View;

import com.Controller.KioskController;
import com.Model.StockModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
        String filePath = "resources\\stock.txt";

        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}