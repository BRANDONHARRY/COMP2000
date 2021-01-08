package com.View;
import com.Model.StockModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

    public KioskView() {
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
    }

    public void barcodeLoad(){
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();

        String text = barcodeTF.getText();
        Integer number = Integer.parseInt(text);
        number --;
//        stockList.getModel(number);

        cartLM.addElement(number);
    }
}





