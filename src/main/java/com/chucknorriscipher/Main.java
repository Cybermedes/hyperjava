package com.chucknorriscipher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Input string:");
        char[] graphemes = getUserInput();
        String[] graphemesBinary = convertCharToBinary(graphemes);

        System.out.println();
        System.out.println("The result:");
        for (int i = 0; i < graphemes.length; i++) {
            System.out.printf("%c = %s%n", graphemes[i], graphemesBinary[i]);
        }
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
}
