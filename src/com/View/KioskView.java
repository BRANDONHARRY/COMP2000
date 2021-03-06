package com.View;
import com.Controller.KioskController;
import com.Model.StockModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class KioskView extends JFrame {
    public JPanel mainPanel;
    public JButton payBtn;
    public JList stockList;
    public JButton loginBtn;
    public JList cartList;
    private JButton checkoutBtn;
    private JTextField barcodeTF;
    private JButton barcodeAddBtn;
    private JLabel stockLbl;
    private JLabel priceLbl;
    public Float total = 0.00f;
    public JOptionPane loginOP;
    public String username;
    public String password;
    public String filePath = "resources\\stock.txt";

    public KioskView(JFrame kioskFrame, JFrame paymentFrame) {
//        Kiosk constructor
        JList test = new JList();
        stockList.setModel(test.getModel());

        cartList.setModel(new DefaultListModel());
        displayStock();

//        Kiosk buttons
        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartStock();
            }
        });
        barcodeAddBtn.addActionListener(new ActionListener() {
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

//        Function to display the stock
    public void displayStock(){
        StockModel newStock = new StockModel();
        newStock.load();
        StockModel[] tempArray = new StockModel[0];
        tempArray = newStock.stock.toArray(tempArray);

        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < tempArray.length; i++){

            listModel.addElement(tempArray[i].getBarcode() +"| " + tempArray[i].getName() +" | " + tempArray[i].getPrice() + " | " + tempArray[i].getStockLevel());
        }
        stockList.setModel(listModel);
    }

    public void cartStock(){
//        Will add the items into the cart from the selected one
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();
        String selected = (String) stockList.getSelectedValue();
        cartLM.addElement(selected);
        updatePrice(selected);
    }

//        Will load an item into the cart from inputted barcode
    public void barcodeLoad(){
        DefaultListModel cartLM = (DefaultListModel) cartList.getModel();
        String result = null;
        String separator = "\\|";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null){

                String[] value = line.split(separator);

                if(value[0].equals(barcodeTF.getText())){
                    result = line;
                    cartLM.addElement(result);
                    updatePrice(result);
                }
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//
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

//    JOptionPane for the admin login
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

//    Accounts funtion to get the username and passwords
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