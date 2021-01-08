package com.View;

import com.Controller.KioskController;
import com.View.KioskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayView {
    public JPanel mainPanel;
    public JList paymentLst;
    private JLabel paymentLbl;
    private JButton returnBtn;
    private JLabel priceLbl;
    private JList tempCartList;

    public PayView(JList cartList){
        tempCartList = cartList;


        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePayment();
                KioskController.openKiosk();
            }
        });
    }
    public void hidePayment(){

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        paymentLst = new JList(tempCartList.getModel());
    }
}