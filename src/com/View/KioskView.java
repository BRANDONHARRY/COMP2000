package com.View;

import com.Controller.StockController;
import com.Model.StockModel;

import javax.swing.*;

public class KioskView extends JFrame {
    private JPanel mainPanel;
    private JButton payBtn;
    private JList stockList;
    private JButton loginBtn;

    public static void main(String[] args){
        JFrame frame = new JFrame("KioskView");
        frame.setContentPane(new KioskView().mainPanel);
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        StockModel model = retriveStock();

        StockModel test = new StockModel();
        StockModel.dataManger dataTest = test.new dataManger();
        dataTest.load();


        StockView view = new StockView();
        StockController controller = new StockController(model, view);

        controller.updateView();

        controller.setBarcode("1111");

        controller.updateView();
        JLabel textLbl = new JLabel();
        textLbl.setText(controller.getBarcode());

    }

    private static StockModel retriveStock(){
        StockModel stockModel = new StockModel();
        stockModel.setBarcode("21764");
        stockModel.setName("Test");
        stockModel.setPrice("£3.99");
        return stockModel;
    }
}