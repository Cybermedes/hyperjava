package com.chucknorriscipher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Input string:");
        char[] graphemes = getUserInput();
        String[] graphemesBinary = convertCharToBinary(graphemes);

        System.out.println();
        System.out.println("The result:");
        System.out.println(encryptMessage(graphemesBinary));

    }

    protected static char[] getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            return input.toCharArray();
        }
    }

    protected static String[] convertCharToBinary(char[] chars) {
        String[] binary = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            binary[i] = String.format("%7s", Integer.toBinaryString(chars[i]))
                    .replace(' ', '0');
        }
        return binary;
    }

    // Encrypt message using Chuck Norris Unary Code technique
    protected static String encryptMessage(String[] graphemesBinary) {
        StringBuilder sb = new StringBuilder();
        String allBinary = String.join("", graphemesBinary);
        int i = 0;

        while (i < allBinary.length()) {

            // Set which char to start counting at the current iteration
            char bit = allBinary.charAt(i);
            int count = 1;
            i++;

            // Start counting the characters and increase index i at the same time
            while (i < allBinary.length() && allBinary.charAt(i) == bit) {
                count++;
                i++;
            }

            // Determine the prefix type
            if (bit == '1') {
                sb.append("0 ");
            } else {
                sb.append("00 ");
            }

            // Append the number of bits in the sequence
            sb.append("0".repeat(Math.max(0, count)));
            sb.append(' ');
        }
        // Remove the empty space at the end of the string
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
