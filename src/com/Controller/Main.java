package com.Controller;


public class Main {
    public static void main(String[] args) {
//        Opens the kiosk
        KioskController kiosk = new KioskController();
        KioskController.openKiosk(kiosk.oldFrame);
    }
}