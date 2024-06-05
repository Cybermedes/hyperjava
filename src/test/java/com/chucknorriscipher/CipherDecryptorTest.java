package com.chucknorriscipher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cipher Decryptor Test")
class CipherDecryptorTest {

    @Test
    @DisplayName("Test message decryption")
    void decryptMessage_EncryptedMessage_returnPlainText() {
        assertAll(
                () -> {
                    String input = "0 0 00 0000 0 000 00 0000 0 00";
                    String expected = "CC";
                    assertEquals(expected, CipherDecryptor.decryptMessage(input));
                },
                () -> {
                    String input = "0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00";
                    String expected = "Hi <3";
                    assertEquals(expected, CipherDecryptor.decryptMessage(input));
                }
        );
    }

    @Test
    @DisplayName("Test message decryption throwing exception")
    void decryptMessage_EncryptedMessage_throwsException() {
        assertAll(
                () -> {
                    String input = "000 0 00 00 0000 0 00 000";
                    assertThrows(IllegalArgumentException.class, () -> CipherDecryptor.decryptMessage(input));
                },
                () -> {
                    String input = "0 0 00 00 0 0 00";
                    assertThrows(IllegalArgumentException.class, () -> CipherDecryptor.decryptMessage(input));
                },
                () -> {
                    String input = "0 0 1 00 0 0 1 000";
                    assertThrows(IllegalArgumentException.class, () -> CipherDecryptor.decryptMessage(input));
                }
        );
    }
}