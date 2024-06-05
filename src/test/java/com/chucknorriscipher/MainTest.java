package com.chucknorriscipher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cipher test")
class MainTest {

    @Test
    @DisplayName("Test if method returns a char array")
    void getUserInput_StringFromInput_ReturnCharArray() {

        assertAll(
                () -> {
                    String inputText = "Hello World!";
                    char[] expected = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
                    ByteArrayInputStream testIn = new ByteArrayInputStream(inputText.getBytes());
                    System.setIn(testIn);
                    assertArrayEquals(expected, Main.getUserInput());
                },
                () -> {
                    String inputText = "Greetings!";
                    char[] expected = {'G', 'r', 'e', 'e', 't', 'i', 'n', 'g', 's', '!'};
                    ByteArrayInputStream testIn2 = new ByteArrayInputStream(inputText.getBytes());
                    System.setIn(testIn2);
                    assertArrayEquals(expected, Main.getUserInput());
                },
                () -> {
                    String inputText = "Who am I?";
                    char[] expected = {'W', 'h', 'o', ' ', 'a', 'm', ' ', 'I', '?'};
                    ByteArrayInputStream testIn3 = new ByteArrayInputStream(inputText.getBytes());
                    System.setIn(testIn3);
                    assertArrayEquals(expected, Main.getUserInput());
                }
        );
    }

    @Test
    @DisplayName("Test conversion char array to binary")
    void convertCharToBinary_getCharArray_returnStringArrayWithBinary() {

        char[] input = {'a', 'h', 'I', '?', '1', ' '};
        String[] expectedOutput = {"1100001", "1101000", "1001001", "0111111", "0110001", "0100000"};
        String[] actualOutput = Main.convertCharToBinary(input);

        assertArrayEquals(expectedOutput, actualOutput);
    }
}