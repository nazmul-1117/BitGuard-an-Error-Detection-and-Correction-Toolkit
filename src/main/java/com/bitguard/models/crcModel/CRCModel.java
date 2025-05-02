package com.bitguard.models.crcModel;

public class CRCModel {

    // XOR two binary strings
    private static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // Perform Mod-2 division
    private static String mod2Division(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);

        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1') {
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
            } else {
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
            }
            pick++;
        }

        // For the last bits
        if (tmp.charAt(0) == '1') {
            tmp = xor(divisor, tmp);
        } else {
            tmp = xor("0".repeat(pick), tmp);
        }

        return tmp;
    }

    // Encode data with CRC
    public static String encodeData(String data, String key) {
        int keyLen = key.length();
        String appendedData = data + "0".repeat(keyLen - 1);
        String remainder = mod2Division(appendedData, key);
        return data + remainder;  // Final transmitted code
    }

    // Check if received data has an error
    public static boolean checkData(String dataWithCRC, String key) {
        String remainder = mod2Division(dataWithCRC, key);
        return remainder.replace("0", "").isEmpty();  // True if all zero => No error
    }
}

