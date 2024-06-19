package com.bullsandcows;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Generator {

    public static String generateRandomNumber(int length) {

        if (length > 10) {
            String message = "Error: can't generate a secret number with a length of " +
                    "11 because there aren't enough unique digits.";
            throw new IllegalArgumentException(message);
        }
        Random random = new Random();
        Set<Integer> digits = new HashSet<>();

        // First digit must not be equal to zero
        int firstDigit = random.nextInt(9) + 1;
        digits.add(firstDigit);

        // StringBuilder to build the number
        StringBuilder sb = new StringBuilder();
        sb.append(firstDigit);

        // Loop until the number has the desired length
        while (sb.length() < length) {
            int nextDigits = random.nextInt(10);
            if (!digits.contains(nextDigits)) {
                digits.add(nextDigits);
                sb.append(nextDigits);
            }
        }
        return sb.toString();
    }
}
