package com.View;
import com.Controller.KioskController;
import com.Model.StockModel;

import javax.swing.*;
import javax.tools.JavaFileManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class KioskView extends JFrame {
    public JPanel mainPanel;
    public JButton payBtn;
    public JList stockList;
    public JButton loginBtn;
    public JList cartList;
    private JButton loadTest;
    private JButton checkoutBtn;
    private JTextField barcodeTF;
    private JButton textBtn;
    private JLabel stockLbl;
    private JLabel priceLbl;
    public Float total = 0.00f;

    public KioskView(JFrame kioskFrame, JFrame paymentFrame) {
        cartList.setModel(new DefaultListModel());

        loadTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStock();
            }
        });
        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartStock();
            }
        });
        textBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barcodeLoad();
            }
        });
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KioskController.openPayment(cartList, total, kioskFrame, paymentFrame);
            }
        });
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void displayStock(){
        StockModel newStock = new StockModel();
        newStock.load();
        StockModel[] tempArray = new StockModel[0];
        tempArray = newStock.stock.toArray(tempArray);

        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < tempArray.length; i++){

            listModel.addElement(tempArray[i].getBarcode() +" | " + tempArray[i].getName() +" | " + tempArray[i].getPrice());
        }
        stockList.setModel(listModel);
    }

    public void cartStock(){
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();
        String selected = (String) stockList.getSelectedValue();
        cartLM.addElement(selected);
        updatePrice(selected);
    }

    public void barcodeLoad(){
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();
        DefaultListModel stockLM = (DefaultListModel) stockList.getModel();

        String text = barcodeTF.getText();
        Integer number = Integer.parseInt(text);
        number --;

        String item = stockLM.elementAt(number).toString();
        cartLM.addElement(item);
        updatePrice(item);
    }
    public void updatePrice(String item){
        String[] itemArray;
        String priceString;
        Float priceFloat;
        String separator = "\\|";

        itemArray = item.split(separator);

        priceString = itemArray[2];
        priceString = priceString.replace(" £", "");
        priceFloat = Float.parseFloat(priceString);
        total += priceFloat;

        priceLbl.setText(String.format("£" + "%.2f",total));

    }
}