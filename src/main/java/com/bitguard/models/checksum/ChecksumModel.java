package com.bitguard.models.checksum;

public class ChecksumModel {

    // Binary addition of two strings
    public static String binaryAdd(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        // Pad the shorter string with zeros
        while (a.length() < b.length()) a = "0" + a;
        while (b.length() < a.length()) b = "0" + b;

        for (int i = a.length() - 1; i >= 0; i--) {
            int bitA = a.charAt(i) - '0';
            int bitB = b.charAt(i) - '0';
            int sum = bitA + bitB + carry;

            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        // Handle overflow (wrap around)
        if (carry == 1) {
            return binaryAdd(result.toString(), "1");
        }

        return result.toString();
    }

    // Calculate checksum for the data
    public static String calculateChecksum(String data, int segmentSize) {
        int length = data.length();
        String sum = "0".repeat(segmentSize);

        for (int i = 0; i < length; i += segmentSize) {
            String segment = data.substring(i, Math.min(i + segmentSize, length));
            // Pad last segment if needed
            if (segment.length() < segmentSize) {
                segment = String.format("%-" + segmentSize + "s", segment).replace(' ', '0');
            }
            sum = binaryAdd(sum, segment);
        }

        // One's complement
        StringBuilder checksum = new StringBuilder();
        for (char c : sum.toCharArray()) {
            checksum.append(c == '0' ? '1' : '0');
        }

        return checksum.toString();
    }

    // Verify received data with checksum
    public static boolean verifyData(String dataWithChecksum, int segmentSize) {
        int length = dataWithChecksum.length();
        String sum = "0".repeat(segmentSize);

        for (int i = 0; i < length; i += segmentSize) {
            String segment = dataWithChecksum.substring(i, Math.min(i + segmentSize, length));
            if (segment.length() < segmentSize) {
                segment = String.format("%-" + segmentSize + "s", segment).replace(' ', '0');
            }
            sum = binaryAdd(sum, segment);
        }

        // Check if sum is all 1s
        return sum.chars().allMatch(c -> c == '1');
    }
}
