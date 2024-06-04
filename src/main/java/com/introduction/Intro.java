package com.introduction;

import java.io.IOException;
import java.io.InputStream;

public class Intro {

    public static void main(String[] args) {
        try (InputStream is = Intro.class.getClassLoader().getResourceAsStream("introduction.txt")) {

            if (is != null) {
                byte[] introTextInBytes = is.readAllBytes();
                String text = new String(introTextInBytes);
                System.out.println(text);
            } else {
                System.out.println("the file introduction.txt is empty");
            }
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }
}
