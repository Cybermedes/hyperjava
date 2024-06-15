package com.numberconverter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number in decimal system: ");
            int decimal = scanner.nextInt();
            System.out.print("Enter target base: ");
            int radix = scanner.nextInt();
            System.out.printf("Conversion result: %s%n", convertDecimal(decimal, radix));
        }
    }

    protected static String convertDecimal(int decimal, int radix) {
        return Integer.toString(decimal, radix);
    }
}
