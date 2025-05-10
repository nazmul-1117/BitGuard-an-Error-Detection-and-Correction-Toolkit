package com.bitguard.models.stuffDeStuff;

public class ByteStuffDeStuff {

    private static final String FLAG = "F";
    private static final String ESC = "E";

    // Stuff FLAG and ESC characters
    public static String stuff(String data) {
        StringBuilder stuffed = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (String.valueOf(c).equals(FLAG) || String.valueOf(c).equals(ESC)) {
                stuffed.append(ESC); // Escape
            }
            stuffed.append(c);
        }
        return FLAG + stuffed + FLAG; // Frame it
    }

    // Remove stuffed ESCs
    public static String destuff(String data) {
        String unframed = data.substring(1, data.length() - 1); // Remove FLAGs
        StringBuilder destuffed = new StringBuilder();

        boolean escape = false;
        for (char c : unframed.toCharArray()) {
            if (escape) {
                destuffed.append(c);
                escape = false;
            } else if (String.valueOf(c).equals(ESC)) {
                escape = true; // Next char is escaped
            } else {
                destuffed.append(c);
            }
        }

        return destuffed.toString();
    }
}

