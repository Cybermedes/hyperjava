package com.bullsandcows;

import java.util.Random;

public class Generator {

    public static String generateSecrete(int secretLength, int symbolsRangeLength) {

        assert secretLength > 0 && secretLength <= 36;
        assert symbolsRangeLength > 0 && symbolsRangeLength <= 36;

        if (symbolsRangeLength < secretLength) {
            String message = "Error: it's not possible to generate a code with a length of %s with %s unique symbols.";
            System.out.printf(message + "%n", secretLength, symbolsRangeLength);
            System.exit(1);
        }

        Random random = new Random();
        StringBuilder secret = new StringBuilder(secretLength);
        String[] allowedChars = "0123456789abcdefghijklmnopqrstuvwxyz".split("");

        while (secret.length() < secretLength) {
            int randomIndex = random.nextInt(symbolsRangeLength);
            String digit = allowedChars[randomIndex];
            if (secret.indexOf(digit) == -1) {
                secret.append(digit);
            }
        }

        String secretAsStars = "*".repeat(secretLength);
        String symbolRange = GameEngine.getSymbolRange(symbolsRangeLength, allowedChars);
        System.out.printf("The secret is prepared: %s (%s).", secretAsStars, symbolRange);
        System.out.println();
        return secret.toString();
    }
}
