package com.readabilityscore;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String text = scanner.nextLine();
            System.out.println(checkReadability(text));
        }
    }

    public static String checkReadability(String text) {

        String[] sentences = text.split("([.!?])");
        int words = 0;
        int numberOfSentences = sentences.length;

        Pattern pattern = Pattern.compile("\\w+");
        for (String sentence : sentences) {
            Matcher matcher = pattern.matcher(sentence);
            while (matcher.find()) {
                words++;
            }
        }

        return (double) words/numberOfSentences <= 10 ? "EASY" : "HARD";
    }
}
