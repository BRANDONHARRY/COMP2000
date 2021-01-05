package com.View;
import com.Model.StockModel;
import com.Controller.StockController;
import java.awt.*;
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

    public KioskView() {
        loadTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel dlm = new DefaultListModel();
                for(int i = 0; i <10; i++){
                    dlm.addElement("test: "+i);
                }
                stockList.setModel(dlm);

//                DefaultListModel listModel = new DefaultListModel();
//                StockModel load = new StockModel();
//
//                String[] loadArray = load.stockData;
//
//                for (int i = 0; i < 10; i++){
//                    listModel.addElement(listModel);
//                }
//                stockList.setModel(listModel);
            }
        });
    }
}





