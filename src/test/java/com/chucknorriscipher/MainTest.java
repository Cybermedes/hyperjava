package com.chucknorriscipher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    @DisplayName("Test getUserInput return")
    void getUserInput() {

        assertAll(
                () -> {
                    String text = "Hello World!";
                    ByteArrayInputStream testIn = new ByteArrayInputStream(text.getBytes());
                    System.setIn(testIn);
                    assertEquals(Main.getUserInput(), "H e l l o   W o r l d ! ");
                },
                () -> {
                    String text2 = "Greetings!";
                    ByteArrayInputStream testIn2 = new ByteArrayInputStream(text2.getBytes());
                    System.setIn(testIn2);
                    assertEquals(Main.getUserInput(), "G r e e t i n g s ! ");
                },
                () -> {
                    String text3 = "Who am I?";
                    ByteArrayInputStream testIn3 = new ByteArrayInputStream(text3.getBytes());
                    System.setIn(testIn3);
                    assertEquals(Main.getUserInput(), "W h o   a m   I ? ");
                }
        );
    }
}