package com.bitguard.helper;

public class BinaryCheck {

    public static boolean validBinary(String data){

        if (!data.matches("[01]+")) {
            return false;
        } else {
            return true;
        }
    }
}
