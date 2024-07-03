package com.readabilityscore;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        ARI ari = new ARI(Path.of(args[0]));
        System.out.println("The text is:");
        ari.getFileContent();

        System.out.println();
        System.out.printf("Words: %d%n", ari.countWords());
        System.out.printf("Sentences: %d%n", ari.countSentences());
        System.out.printf("Characters: %d%n", ari.countCharacters());
        System.out.printf("The score is: %.2f%n", ari.calculateIndex());
        System.out.printf("This test should be understood by %s years-old.%n", ari.getAgeRange());
    }
}
