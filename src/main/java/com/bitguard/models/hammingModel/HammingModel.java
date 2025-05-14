package com.bitguard.models.hammingModel;

public class HammingModel {

    public static int errorFoundPosition;

    // Calculate required parity bits for data bits
    public static int calculateParityBits(int m) {
        int p = 0;
        while ((1 << p) < (m + p + 1)) {
            p++;
        }
        return p;
    }

    // Calculate how many parity bits are present based on total codeword length
    private static int calculateParityBitsFromCodewordLength(int n) {
        int p = 0;
        while ((1 << p) < (n + 1)) {
            p++;
        }
        return p;
    }

    // Utility: Check if a number is a power of two
    private static boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0 && x != 0;
    }

    // Encode data using Hamming Code (even/odd parity)
    public static String encodeHammingCode(String data, String parityType) {
        int m = data.length();
        int p = calculateParityBits(m);
        int n = m + p;
        char[] codeword = new char[n];

        int dataIndex = 0;
        for (int i = 1; i <= n; i++) {
            if (isPowerOfTwo(i)) {
                codeword[i - 1] = '0'; // Placeholder for parity bits
            } else {
                codeword[i - 1] = data.charAt(dataIndex++);
            }
        }

        for (int i = 0; i < p; i++) {
            int parityPos = 1 << i;
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (((j >> i) & 1) == 1) {
                    if (codeword[j - 1] == '1') {
                        count++;
                    }
                }
            }

            if (parityType.equalsIgnoreCase("even")) {
                codeword[parityPos - 1] = (count % 2 == 0) ? '0' : '1';
            } else {
                codeword[parityPos - 1] = (count % 2 == 0) ? '1' : '0';
            }
        }

        return new String(codeword);
    }

    // Detect and correct single-bit errors, print error position
    public static String detectAndCorrectErrors(String codeword, String parityType) {
        int n = codeword.length();
        int p = calculateParityBitsFromCodewordLength(n);
        int errorPosition = 0;

        for (int i = 0; i < p; i++) {
            int parityPos = 1 << i;
            int count = 0;

            for (int j = 1; j <= n; j++) {
                if (((j >> i) & 1) == 1) {
                    if (codeword.charAt(j - 1) == '1') {
                        count++;
                    }
                }
            }

            if (parityType.equalsIgnoreCase("even") && count % 2 != 0) {
                errorPosition += parityPos;
            } else if (parityType.equalsIgnoreCase("odd") && count % 2 == 0) {
                errorPosition += parityPos;
            }
        }

        if (errorPosition > 0 && errorPosition <= n) {
            System.out.println("⚠️ Error detected at position: " + errorPosition);
            errorFoundPosition = errorPosition;
            char[] corrected = codeword.toCharArray();
            corrected[errorPosition - 1] = (corrected[errorPosition - 1] == '1') ? '0' : '1';
            return new String(corrected);
        }

        System.out.println("✅ No error detected.");
        return codeword;
    }

    // Extract original data bits from a Hamming codeword
    public static String extractDataBits(String codeword) {
        StringBuilder data = new StringBuilder();
        for (int i = 1; i <= codeword.length(); i++) {
            if (!isPowerOfTwo(i)) {
                data.append(codeword.charAt(i - 1));
            }
        }
        return data.toString();
    }

    // Main method for demonstration
//    public static void main(String[] args) {
//        String data = "0101";
//        String parity = "even";
//
//        System.out.println("Original Data: " + data);
//
//        // Encoding
//        String encoded = encodeHammingCode(data, parity);
//        System.out.println("Encoded Hamming Code: " + encoded);
//
//        // Simulate single-bit error (flip bit at index 2)
//        char[] tampered = encoded.toCharArray();
//        tampered[2] = (tampered[2] == '1') ? '0' : '1';
//        String received = new String(tampered);
//        System.out.println("Received with Error: " + received);
//
//        // Detect and Correct
//        String corrected = detectAndCorrectErrors(received, parity);
//        System.out.println("Corrected Codeword: " + corrected);
//
//        // Extract data
//        String original = extractDataBits(corrected);
//        System.out.println("Extracted Data: " + original);
//    }
}
