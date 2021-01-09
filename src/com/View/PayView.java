package com.View;

import com.Controller.KioskController;
import com.View.KioskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PayView {
    public JPanel mainPanel;
    public JList paymentLst;
    private JLabel paymentLbl;
    private JButton returnBtn;
    private JLabel totalLbl;
    private JButton cashBtn;
    private JButton cardBtn;
    private JTextArea receiptTextArea;
    public JList tempCartList;
    public JOptionPane popUp;

    public PayView(JList cartList, Float tempTotal, JFrame kioskFrame, JFrame paymentFrame){
        tempCartList = cartList;

        totalLbl.setText(String.format("£" + "%.2f", tempTotal));


        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KioskController.openKiosk(kioskFrame, paymentFrame);
            }
        });
        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashCheck();
            }
        });
        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardCheck();
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        paymentLst = new JList(tempCartList.getModel());
    }
    public void cashCheck(){
        JFrame cashPopup = new JFrame();
        cashPopup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        String tempCash = popUp.showInputDialog(cashPopup, "Enter Cash: ");
        String tempTotal = totalLbl.getText();
        tempTotal = tempTotal.replace("£", "");
        Float cash = Float.parseFloat(tempCash);
        Float total = Float.parseFloat(tempTotal);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        Float change = 0.00f;

        if (cash >= total){
            change = cash - total;
        }
        else{
            popUp.showMessageDialog(cashPopup, "Not enough money given. Try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        receiptTextArea.setText("Brandon's Bananas & More \n" +
                "Date & Time: " + formatter.format(date) + "\n" +
                "Receipt: \n" +
                "Items: " + paymentLst.getModel().toString() + "\n" +
                "Total: " + totalLbl.getText() + "\n" +
                "Cash given: £" + tempCash + "\n" +
                "Change: " + (String.format("£" + "%.2f",change)));
    }
    public void cardCheck(){
        JFrame popUpFrame = new JFrame();
        popUpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        int answer = popUp.showConfirmDialog(popUpFrame, "Accept card: ", "Bank" , JOptionPane.INFORMATION_MESSAGE);
        if(answer==JOptionPane.YES_OPTION){
            popUp.showMessageDialog(popUpFrame, "Card Accepted", "Bank" , JOptionPane.INFORMATION_MESSAGE);
            receiptTextArea.setText("Receipt: \n" +
                    "Items: " + paymentLst.getModel() + "\n" +
                    "Total: " + totalLbl.getText() + "\n" +
                    "Card accepted and charged.");
        }
        else{
            popUp.showMessageDialog(popUpFrame, "Card Declined.", "Bank" , JOptionPane.INFORMATION_MESSAGE);
            receiptTextArea.setText("Card Declined try again.");
        }
    }
}