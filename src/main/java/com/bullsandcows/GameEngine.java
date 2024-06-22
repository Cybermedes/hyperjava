package com.bullsandcows;

import java.util.Scanner;

public class GameEngine {

    private static int readNumber(Scanner scanner) {
        int number = 0;
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            String userInput = scanner.next();
            System.out.printf("Error: %s isn't a valid number.%n", userInput);
            System.exit(1);
        }
        return number;
    }

    protected static int getSecretLength(Scanner scanner) {
        System.out.println("Input the length of the secret code:");
        int secretLength = readNumber(scanner);

        if (secretLength > 36) {
            System.out.println("Error: secret length cannot be greater than 36");
            System.exit(1);
        } else if (secretLength < 1) {
            System.out.println("Error: secret length cannot be less than 1");
            System.exit(1);
        }
        return secretLength;
    }

    protected static int getSymbolsRangeLength(Scanner scanner) {
        System.out.println("Input the number of possible symbols in the code:");
        int symbolsRangeLength = readNumber(scanner);

        if (symbolsRangeLength > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(1);
        } else if (symbolsRangeLength < 1) {
            System.out.println("Error: symbols range length cannot be less than 1");
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
