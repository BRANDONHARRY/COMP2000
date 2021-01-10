package com.View;
import com.Controller.KioskController;
import com.Model.StockModel;

import javax.swing.*;
import javax.tools.JavaFileManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KioskView extends JFrame {
    public JPanel mainPanel;
    public JButton payBtn;
    public JList stockList;
    public JButton loginBtn;
    public JList cartList;
    private JButton checkoutBtn;
    private JTextField barcodeTF;
    private JButton textBtn;
    private JLabel stockLbl;
    private JLabel priceLbl;
    public Float total = 0.00f;
    public JOptionPane loginOP;
    public String username;
    public String password;

    public KioskView(JFrame kioskFrame, JFrame paymentFrame) {
        cartList.setModel(new DefaultListModel());
        displayStock();

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
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KioskController.openPayment(cartList, total, kioskFrame, paymentFrame);
            }
        });
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(kioskFrame, stockList);
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

            listModel.addElement(tempArray[i].getBarcode() +" | " + tempArray[i].getName() +" | " + tempArray[i].getPrice() + " | " + tempArray[i].getStockLevel());
        }
        stockList.setModel(listModel);
    }

    public void cartStock(){
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();
        String selected = (String) stockList.getSelectedValue();
        cartLM.addElement(selected);
        updatePrice(selected);
    }

    public void barcodeLoad(){
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();
        DefaultListModel stockLM = (DefaultListModel) stockList.getModel();

        String text = barcodeTF.getText();
        Integer number = Integer.parseInt(text);
        number --;

        String item = stockLM.elementAt(number).toString();
        cartLM.addElement(item);
        updatePrice(item);
    }
    public void updatePrice(String item){
        String[] itemArray;
        String priceString;
        Float priceFloat;
        String separator = "\\|";

        itemArray = item.split(separator);

        priceString = itemArray[2];
        priceString = priceString.replace(" £", "");
        priceFloat = Float.parseFloat(priceString);
        total += priceFloat;

        priceLbl.setText(String.format("£" + "%.2f",total));
    }
    public void login(JFrame kioskFrame, JList stockList){
        JFrame popUpFrame = new JFrame();
        popUpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JTextField usernameTf = new JTextField();
        JPasswordField passwordTf = new JPasswordField();

        accounts();

        Object[] message = {
                "Username: ", usernameTf,
                "Password: ", passwordTf
        };

        int option = loginOP.showConfirmDialog(popUpFrame, message, "Login", JOptionPane.OK_CANCEL_OPTION);

        if(option == loginOP.OK_OPTION){
            if(usernameTf.getText().equals(username) && passwordTf.getText().equals(password)){
                loginOP.showMessageDialog(popUpFrame, "Login Correct.", "Login", JOptionPane.INFORMATION_MESSAGE);
                KioskController.openAdmin(kioskFrame, stockList);
            }
            else {
                loginOP.showMessageDialog(popUpFrame, "Login failed.", "Login", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            loginOP.showMessageDialog(popUpFrame, "Login failed.", "Login", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void accounts(){
        String filePath = "resources\\accounts.txt";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            username = scanner.nextLine();
            password = scanner.nextLine();
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}