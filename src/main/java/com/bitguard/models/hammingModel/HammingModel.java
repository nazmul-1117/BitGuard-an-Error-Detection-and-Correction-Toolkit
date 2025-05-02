package com.bitguard.models.hammingModel;

public class HammingModel {

    // Method to calculate the number of parity bits required for n data bits
    public static int calculateParityBits(int n) {
        int p = 0;
        while ((1 << p) < (n + p + 1)) {
            p++;
        }
        return p;
    }

    // Method to encode data using Hamming Code
    public static String encodeHammingCode(String data, String parityType) {
        int m = data.length();  // Number of data bits
        int p = calculateParityBits(m);  // Number of parity bits
        int n = m + p;  // Total length of the codeword (data + parity bits)

        // Initialize an array to hold the codeword
        char[] codeword = new char[n];

        // Fill the codeword array with data bits and placeholder for parity bits
        int dataIndex = 0;
        for (int i = 0; i < n; i++) {
            if ((i & (i + 1)) == 0) {
                codeword[i] = '0';  // Placeholder for parity bits
            } else {
                codeword[i] = data.charAt(dataIndex++);
            }
        }

        // Calculate and set parity bits
        for (int i = 0; i < p; i++) {
            int parityBitPosition = (1 << i);  // Parity bit is at position 2^i
            int count = 0;

            // Check bits in positions related to parity bit
            for (int j = parityBitPosition - 1; j < n; j += 2 * parityBitPosition) {
                for (int k = j; k < Math.min(j + parityBitPosition, n); k++) {
                    if (codeword[k] == '1') {
                        count++;
                    }
                }
            }

            // Set parity bit to even or odd based on user input
            if (parityType.equalsIgnoreCase("even")) {
                codeword[parityBitPosition - 1] = (count % 2 == 0) ? '0' : '1';
            } else {
                codeword[parityBitPosition - 1] = (count % 2 == 0) ? '1' : '0';
            }
        }

        return new String(codeword);
    }

    // Method to detect and correct errors (Hamming Error Detection)
    public static String detectAndCorrectErrors(String codeword, String parityType) {
        int n = codeword.length();
        int p = calculateParityBits(n - 1);  // Number of parity bits
        int errorPosition = 0;

        // Check the parity bits
        for (int i = 0; i < p; i++) {
            int parityBitPosition = (1 << i);
            int count = 0;

            // Check bits related to parity bit position
            for (int j = parityBitPosition - 1; j < n; j += 2 * parityBitPosition) {
                for (int k = j; k < Math.min(j + parityBitPosition, n); k++) {
                    if (codeword.charAt(k) == '1') {
                        count++;
                    }
                }
            }

            if (parityType.equalsIgnoreCase("even") && count % 2 != 0) {
                errorPosition += parityBitPosition;
            } else if (parityType.equalsIgnoreCase("odd") && count % 2 == 0) {
                errorPosition += parityBitPosition;
            }
        }

        // If errorPosition is 0, no error
        if (errorPosition != 0) {
            // Correct the error by flipping the bit at errorPosition
            char[] correctedCodeword = codeword.toCharArray();
            correctedCodeword[errorPosition - 1] = (correctedCodeword[errorPosition - 1] == '1') ? '0' : '1';
            return new String(correctedCodeword);
        }

        return codeword;  // No error
    }

}

