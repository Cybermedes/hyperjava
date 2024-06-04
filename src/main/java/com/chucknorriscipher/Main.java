package com.chucknorriscipher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        getUserInput();
    }

    private static void getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input string:");
            String input = scanner.nextLine();

            System.out.println();
            char[] characters = input.toCharArray();
            for (char character : characters) {
                System.out.printf("%c ", character);
            }
        }
    }
}
