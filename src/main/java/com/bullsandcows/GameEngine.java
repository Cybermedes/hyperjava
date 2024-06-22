package com.bullsandcows;

import java.util.Scanner;

public class GameEngine {

    protected static int getSecretLength(Scanner scanner) {
        System.out.println("Input the length of the secret code:");
        int secretLength = scanner.nextInt();

        while (secretLength > 36) {
            System.out.println("Error: secret length cannot be greater than 36");
            System.out.println("Input the length of the secret code:");
            secretLength = scanner.nextInt();
        }
        return secretLength;
    }

    protected static int getSymbolsRangeLength(Scanner scanner, int secretLength) {
        System.out.println("Input the number of possible symbols in the code:");
        int symbolsRangeLength = scanner.nextInt();

        while (symbolsRangeLength > 36 || symbolsRangeLength < secretLength) {
            if (symbolsRangeLength > 36) {
                System.out.println("Error: symbols range length cannot be greater than 36");
            } else {
                System.out.println("Error: symbols range length cannot be smaller than secret length");
            }
            System.out.println("Input the number of possible symbols in the code:");
            symbolsRangeLength = scanner.nextInt();
        }
        return symbolsRangeLength;
    }

    protected static String getSymbolRange(int symbolsRangeLength, String[] allowedChars) {
        if (symbolsRangeLength < 10) {
            return "0-" + allowedChars[symbolsRangeLength - 1];
        } else {
            return "0-9, a-" + allowedChars[symbolsRangeLength - 1];
        }
    }

    protected static String getGradeMessage(Grade grade) {
        StringBuilder cowsAndBulls = new StringBuilder();
        if (grade.cows() == 0 && grade.bulls() == 0) {
            cowsAndBulls.append("None");
        } else {
            cowsAndBulls.append("Grade: ");
            if (grade.bulls() > 0) {
                cowsAndBulls.append(grade.bulls()).append(" bull");
                if (grade.bulls() > 1) {
                    cowsAndBulls.append("s");
                }
                if (grade.cows() > 0) {
                    cowsAndBulls.append(" and ").append(grade.cows()).append(" cow");
                    if (grade.cows() > 1) {
                        cowsAndBulls.append("s");
                    }
                }
            } else {
                cowsAndBulls.append(grade.cows()).append(" cow");
                if (grade.cows() > 1) {
                    cowsAndBulls.append("s");
                }
            }
        }
        return cowsAndBulls.toString();
    }
}
