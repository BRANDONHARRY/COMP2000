package com.View;

import com.Controller.KioskController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView {
    public JList addStockList;
    public JPanel mainPanel;
    public JButton logOutBtn;
    public JButton addBtn;

    public AdminView(JFrame adminFrame, JList stockList) {



        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KioskController.openKiosk(adminFrame);

            }
        });
    }
}
