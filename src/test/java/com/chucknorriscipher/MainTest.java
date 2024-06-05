package com.chucknorriscipher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cipher test")
class MainTest {

    @Test
    @DisplayName("Test conversion char array to binary")
    void convertCharToBinary_getCharArray_returnStringArrayWithBinary() {

        char[] input = {'a', 'h', 'I', '?', '1', ' '};
        String[] expectedOutput = {"1100001", "1101000", "1001001", "0111111", "0110001", "0100000"};
        String[] actualOutput = Main.convertCharToBinary(input);

        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("Test message encryption")
    void encryptMessage_BinaryStringArray_returnEncryptedString() {
        assertAll(
                () -> {
                    String input = "C";
                    char[] chars = input.toCharArray();
                    String[] strings = Main.convertCharToBinary(chars);
                    String expected = "0 0 00 0000 0 00";
                    assertEquals(expected, Main.encryptMessage(strings));
                },
                () -> {
                    String input = "CC";
                    char[] chars = input.toCharArray();
                    String[] strings = Main.convertCharToBinary(chars);
                    String expected = "0 0 00 0000 0 000 00 0000 0 00";
                    assertEquals(expected, Main.encryptMessage(strings));
                },
                () -> {
                    String input = "Hi <3";
                    char[] chars = input.toCharArray();
                    String[] strings = Main.convertCharToBinary(chars);
                    String expected = "0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00";
                    assertEquals(expected, Main.encryptMessage(strings));
                }
        );
    }
}