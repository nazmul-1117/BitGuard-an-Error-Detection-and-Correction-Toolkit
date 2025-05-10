package com.bitguard.models.stuffDeStuff;

public class BitStuffingDeStuffing {

    // Stuff 0 after five consecutive 1s
    public static String stuff(String data) {
        StringBuilder stuffed = new StringBuilder();
        int count = 0;

        System.out.println("in bit stuffing got: " + data);

        for (char c : data.toCharArray()) {
            if (c == '1') {
                count++;
                stuffed.append('1');
                if (count == 5) {
                    stuffed.append('0'); // Stuff 0
                    count = 0;
                }
            } else {
                stuffed.append('0');
                count = 0;
            }
        }

        return stuffed.toString();
    }

    // Remove stuffed 0 after five 1s
    public static String destuff(String data) {
        StringBuilder destuffed = new StringBuilder();
        int count = 0;

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == '1') {
                count++;
                destuffed.append('1');
            } else {
                if (count == 5) {
                    // Skip stuffed 0
                    count = 0;
                    continue;
                }
                destuffed.append('0');
                count = 0;
            }
        }

        return destuffed.toString();
    }
}
