package com.Controller;


import com.Model.StockModel;
import com.View.AdminView;
import com.View.KioskView;
import com.View.PayView;
import com.View.StockView;

import javax.swing.*;
import java.util.Arrays;
import java.util.Vector;


public class KioskController {
    public JFrame oldFrame;

    public static void openKiosk(JFrame oldFrame){
        JFrame kioskFrame = new JFrame("KioskView");
        kioskFrame.setContentPane(new KioskView(kioskFrame, oldFrame).mainPanel);
        kioskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kioskFrame.setSize(1000, 700);
        kioskFrame.setVisible(true);
        oldFrame.setVisible(false);

        StockModel data = new StockModel();
        data.load();
    }
    public static void openPayment(JList cartList, Float total, JFrame kioskFrame, JFrame paymentFrame){
        paymentFrame = new JFrame("PaymentView");
        paymentFrame.setContentPane(new PayView(cartList, total, kioskFrame, paymentFrame).mainPanel);
        paymentFrame.setSize(900, 600);
        paymentFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        paymentFrame.setVisible(true);
        kioskFrame.setVisible(false);
    }
    public static void openAdmin(JFrame kioskFrame, JList stockList){
        JFrame adminFrame = new JFrame("Admin");
        adminFrame.setContentPane(new AdminView(adminFrame, stockList).mainPanel);
        adminFrame.setSize(900,600);
        adminFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        adminFrame.setVisible(true);
        kioskFrame.setVisible(false);
    }
}