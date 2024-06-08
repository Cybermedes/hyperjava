package com.bullsandcows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String userGuess = scanner.nextLine();
            String secretCode = "9305";
            System.out.println(getCowsAndBulls(userGuess, secretCode));
        }
    }

    protected static String getCowsAndBulls(String input, String secret) {

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            if (input.charAt(i) == secret.charAt(i)) {
                bulls++;
            } else if (input.contains(String.valueOf(secret.charAt(i)))) {
                cows++;
            }
        }

        String noCows = "%d bull(s)".formatted(bulls);
        String noBulls = "%d cow(s)".formatted(cows);
        String bullsAndCows = "%d bull(s) and %d cow(s)".formatted(bulls, cows);
        String grade = "Grade: %s. The secret code is %s.";

        if (bulls == 0 && cows == 0) {
            return String.format("Grade: None. The secret code is %s.", secret);
        } else if (cows == 0 && bulls > 0) {
            return String.format(grade, noCows, secret);
        } else if (bulls == 0 && cows > 0) {
            return String.format(grade, noBulls, secret);
        } else {
            return String.format(grade, bullsAndCows, secret);
        }
    }
}
