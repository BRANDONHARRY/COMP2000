package com.Controller;


import com.Model.StockModel;
import com.View.KioskView;
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

        StockModel load = new StockModel();
        StockModel.dataManager data = load.new dataManager();
        data.load();

    }
}
