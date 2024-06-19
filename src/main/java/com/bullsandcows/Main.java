package com.bullsandcows;

import java.util.Scanner;

public class Main {

    private static String secretCode;
    private static int cows = 0;
    private static int bulls = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please, enter the secret code's length:");
            int numberOfDigits = Integer.parseInt(scanner.nextLine());
            System.out.println("Okay, let's start a game!");
            secretCode = Generator.generateRandomNumber(numberOfDigits);

            for (int i = 1;; i++) {
                System.out.printf("Turn %d:%n", i);
                checkUserInput(scanner);
                System.out.println(getGradeMessage());

                if (bulls == secretCode.length()) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void checkUserInput(Scanner scanner) {
        String input = scanner.nextLine();
        for (int i = 0; i < secretCode.length(); i++) {
            if (input.charAt(i) == secretCode.charAt(i)) {
                bulls++;
            } else if (input.contains(String.valueOf(secretCode.charAt(i)))) {
                cows++;
            }
        }
    }

    private static String getGradeMessage() {

        StringBuilder cowsAndBulls = new StringBuilder();
        if (cows == 0 && bulls == 0) {
            cowsAndBulls.append("None");
        } else {
            cowsAndBulls.append("Grade: ");
            if (bulls > 0) {
                cowsAndBulls.append(bulls).append(" bull");
                if (bulls > 1) {
                    cowsAndBulls.append("s");
                }
                if (cows > 0) {
                    cowsAndBulls.append(" and ").append(cows).append(" cow");
                    if (cows > 1) {
                        cowsAndBulls.append("s");
                    }
                }
            } else {
                cowsAndBulls.append(cows).append(" cow");
                if (cows > 1) {
                    cowsAndBulls.append("s");
                }
            }
        }
        return cowsAndBulls.toString();
    }
}
