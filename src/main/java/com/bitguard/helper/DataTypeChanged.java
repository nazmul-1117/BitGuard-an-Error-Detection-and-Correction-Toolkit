package com.bitguard.helper;

public class DataTypeChanged {

    public static int stringToInt(String data){
        int convertedData = 0;
        try {
            convertedData = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            convertedData = 0;
        }

        return convertedData;
    }

    public static float stringToFloat(String s){
        float n;
        try {
            n = Float.parseFloat(s);
        }catch (NumberFormatException nx){
            n = -0.5F;
        }
        return n;
    }

    public static String intToString(int data){
        return data+"";
    }
}
