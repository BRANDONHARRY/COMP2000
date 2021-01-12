package com.View;

import com.Controller.KioskController;
import com.Model.StockModel;
import com.View.KioskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PayView {
    public JPanel mainPanel;
    public JList paymentLst;
    public JList tempCartList;
    private JLabel paymentLbl;
    private JButton returnBtn;
    private JLabel totalLbl;
    private JButton cashBtn;
    private JButton cardBtn;
    private JTextArea receiptTextArea;
    private JButton receiptBtn;
    public JOptionPane popUp;
    public String filePath = "resources\\stock.txt";
    public JFrame test;

    public PayView(JList cartList, Float tempTotal, JFrame kioskFrame, JFrame paymentFrame){
        tempCartList = cartList;
        totalLbl.setText(String.format("£" + "%.2f", tempTotal));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateTemp = new Date();
        String date = dateFormat.format(dateTemp);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date timeTemp = new Date();
        String time = timeFormat.format(timeTemp);

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KioskController.openKiosk(paymentFrame);
            }
        });
        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashCheck(date, time);
            }
        });
        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardCheck(date, time);
            }
        });
        receiptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printReceipt();
            }
        });
    }
    private void createUIComponents() {
        paymentLst = new JList(tempCartList.getModel());
    }
    public void cashCheck(String date, String time){
        JFrame cashPopup = new JFrame();
        cashPopup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        String tempCash = popUp.showInputDialog(cashPopup, "Enter Cash: ");
        String tempTotal = totalLbl.getText();
        tempTotal = tempTotal.replace("£", "");
        Float cash = Float.parseFloat(tempCash);
        Float total = Float.parseFloat(tempTotal);

        Float change = 0.00f;

        if (cash >= total){
            change = cash - total;
        }
        else{
            popUp.showMessageDialog(cashPopup, "Not enough money given. Try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        receiptTextArea.setText("Brandon's Bananas & More \n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Receipt: \n" +
                "Items: " + paymentLst.getModel().toString() + "\n" +
                "Total: " + totalLbl.getText() + "\n" +
                "Cash given: £" + tempCash + "\n" +
                "Change: " + (String.format("£" + "%.2f",change)));
        updateStock();
    }
    public void cardCheck(String date, String time){
        JFrame popUpFrame = new JFrame();
        popUpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        int answer = popUp.showConfirmDialog(popUpFrame, "Accept card: ", "Bank" , JOptionPane.INFORMATION_MESSAGE);
        if(answer==JOptionPane.YES_OPTION){
            popUp.showMessageDialog(popUpFrame, "Card Accepted", "Bank" , JOptionPane.INFORMATION_MESSAGE);
            receiptTextArea.setText("Brandon's Bananas & More \n" +
                    "Date: " + date + "\n" +
                    "Time: " + time + "\n" +
                    "Receipt: \n" +
                    "Items: " + paymentLst.getModel() + "\n" +
                    "Total: " + totalLbl.getText() + "\n" +
                    "Card accepted and charged.");
            updateStock();
        }
        else{
            popUp.showMessageDialog(popUpFrame, "Card Declined.", "Bank" , JOptionPane.INFORMATION_MESSAGE);
            receiptTextArea.setText("Card Declined try again.");
        }
    }
    public void printReceipt(){
        JFrame popUpFrame = new JFrame();
        popUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        popUp.showMessageDialog(popUpFrame,receiptTextArea.getText(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
    public void updateStock(){
        StockModel stockArray = new StockModel();


        DefaultListModel paymentLM = (DefaultListModel) paymentLst.getModel();

        for(int i = 0; i < paymentLM.size(); i++){
            String paymentGetElement = (String) paymentLM.getElementAt(i);
            String[] splitArray = paymentGetElement.split("\\|");
            String barcodeFromArray = splitArray[0];

            for(int j = 0; j < stockArray.stock.size(); j++){
                StockModel currentModel = stockArray.stock.get(j);
                String getBarcode = currentModel.getBarcode();

                if (getBarcode.equals(barcodeFromArray)){
                    currentModel.setStockLevel(currentModel.getStockLevel()-1);
                    save(currentModel);
                }
            }
        }
    }
    public void save(StockModel tempProduct){
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
}