package com.Controller;
import com.Controller.*;
import com.View.KioskView;


public class Main {
    public static void main(String[] args) {
        KioskController kiosk = new KioskController();
        KioskController.openKiosk(kiosk.paymentFrame);
    }
}