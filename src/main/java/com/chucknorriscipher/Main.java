package com.chucknorriscipher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine();

            switch (operation) {
                case "encode" -> {
                    System.out.println("Input string:");
                    String input = scanner.nextLine();
                    char[] graphemes = input.toCharArray();
                    String[] graphemesBinary = convertCharToBinary(graphemes);
                    String encryptedMessage = encryptMessage(graphemesBinary);
                    System.out.println(encryptedMessage);
                    System.out.println();
                }
                case "decode" -> {
                    System.out.println("Input encoded string:");
                    String encryptedMessage = scanner.nextLine();
                    try {
                        String plainText = CipherDecryptor.decryptMessage(encryptedMessage);
                        System.out.println("Decoded string:");
                        System.out.println(plainText);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Encoded string is not valid.");
                    }
                    System.out.println();
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.printf("There is no '%s' operation%n%n", operation);
            }
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
