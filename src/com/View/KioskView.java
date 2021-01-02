package com.View;

import com.Controller.StockController;
import com.Model.StockModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KioskView extends JFrame {
    private JPanel mainPanel;
    private JTextArea viewText;





    public static void main(String[] args){
        JFrame frame = new JFrame("KioskView");
        frame.setContentPane(new KioskView().mainPanel);
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        StockModel model = retriveStock();
        StockView view = new StockView();
        StockController controller = new StockController(model, view);

        controller.updateView();

        controller.setBarcode("1111");

        controller.updateView();
    }

    private static StockModel retriveStock(){
        StockModel stockModel = new StockModel();
        stockModel.setBarcode("21764");
        stockModel.setName("Test");
        stockModel.setPrice("£3.99");
        return stockModel;
    }
}
