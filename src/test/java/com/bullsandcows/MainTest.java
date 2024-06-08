package com.bullsandcows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Bulls & Cows")
class MainTest {

    @Test
    @DisplayName("Test Number of Cows & Bulls return")
    void testGetCowsAndBulls_returnFormattedString() {
        String secretCode = "9305";
        assertAll(
                () -> {
                    String input = "1234";
                    String expected = "Grade: 1 cow(s). The secret code is 9305.";
                    assertEquals(expected, Main.getCowsAndBulls(input, secretCode));
                },
                () -> {
                    String input = "9087";
                    String expected = "Grade: 1 bull(s) and 1 cow(s). The secret code is 9305.";
                    assertEquals(expected, Main.getCowsAndBulls(input, secretCode));
                },
                () -> {
                    String input = "1267";
                    String expected = "Grade: None. The secret code is 9305.";
                    assertEquals(expected, Main.getCowsAndBulls(input, secretCode));
                }
        );
    }
}