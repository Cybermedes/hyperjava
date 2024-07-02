package com.numberconverter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String userPrompt;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
                userPrompt = scanner.nextLine();
                switch (userPrompt) {
                    case "/exit" -> System.exit(0);
                    case "/from" -> System.out.println(convertFromDecimalToBase(scanner));
                    case "/to" -> System.out.println(convertFromBaseToDecimal(scanner));
                    default -> System.out.println("unknown command");
                }
                scanner.nextLine();
                System.out.println();
            }
        }
    }

    protected static String convertFromDecimalToBase(Scanner scanner) {
        System.out.print("Enter a number in decimal system: ");
        int decimal = scanner.nextInt();
        System.out.print("Enter the target base: ");
        int base = scanner.nextInt();
        return "Conversion result: " + Integer.toString(decimal, base);
    }

    protected static String convertFromBaseToDecimal(Scanner scanner) {
        System.out.print("Enter source number: ");
        String number = scanner.nextLine();
        System.out.print("Enter source base: ");
        int base = scanner.nextInt();
        return "Conversion to decimal result: " + Integer.parseInt(number,base);
    }

}
