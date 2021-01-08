package com.Controller;


import com.Model.StockModel;
import com.View.KioskView;
import com.View.PayView;
import com.View.StockView;

import javax.swing.*;
import java.util.Arrays;
import java.util.Vector;


public class KioskController {
    public static void openKiosk(){
        JFrame frame = new JFrame("KioskView");
        frame.setContentPane(new KioskView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);

        StockModel data = new StockModel();
        data.load();
    }
    public static void openPayment(JList cartList){
        JFrame frame = new JFrame("PaymentView");
        frame.setContentPane(new PayView(cartList).mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setVisible(true);

        PayView view = new PayView(cartList);
    }
}