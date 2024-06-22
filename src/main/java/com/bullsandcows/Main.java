package com.bullsandcows;

import java.util.Scanner;

public class Main {

    private static String secretCode;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            // Game intro
            int secretLength = GameEngine.getSecretLength(scanner);
            int numberOfSymbols = GameEngine.getSymbolsRangeLength(scanner);
            secretCode = Generator.generateSecrete(secretLength, numberOfSymbols);
            System.out.println("Okay, let's start a game!");

            // Start REPL mode with turn counter
            for (int i = 1; ; i++) {
                System.out.printf("Turn %d:%n", i);
                String userGuess = scanner.nextLine();
                Grade grade = getGrade(userGuess);
                System.out.println(GameEngine.getGradeMessage(grade));

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
}
