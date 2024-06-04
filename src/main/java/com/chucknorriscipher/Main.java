package com.chucknorriscipher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(getUserInput());
    }

    protected static String getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input string:");
            String input = scanner.nextLine();

            System.out.println();
            char[] characters = input.toCharArray();
            StringBuilder sb = new StringBuilder();

            for (char character : characters) {
                sb.append(character).append(" ");
            }
            return sb.toString();
        }
    }
}
