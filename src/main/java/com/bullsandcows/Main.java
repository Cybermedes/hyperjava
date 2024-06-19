package com.bullsandcows;

import java.util.Scanner;

public class Main {

    private static String secretCode;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            // Game intro
            System.out.println("Please, enter the secret code's length:");
            int numberOfDigits = Integer.parseInt(scanner.nextLine());
            System.out.println("Okay, let's start a game!");
            secretCode = Generator.generateRandomNumber(numberOfDigits);

            // Start REPL mode with turn counter
            for (int i = 1; ; i++) {
                System.out.printf("Turn %d:%n", i);
                String userGuess = scanner.nextLine();
                Grade grade = getGrade(userGuess);
                System.out.println(getGradeMessage(grade));

                // Break loop when user's answer match secret code
                if (userGuess.equals(secretCode)) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static Grade getGrade(String guess) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bulls += 1;
            } else if (secretCode.indexOf(guess.charAt(i)) != -1) {
                cows += 1;
            }
        }
        return new Grade(bulls, cows);
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
