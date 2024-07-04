package com.readabilityscore;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            TextProperties textProperties = new TextProperties(Path.of(args[0]));
            System.out.println("The text is:");
            textProperties.getFileContent();

            System.out.println();
            System.out.printf("Words: %d%n", textProperties.countWords());
            System.out.printf("Sentences: %d%n", textProperties.countSentences());
            System.out.printf("Characters: %d%n", textProperties.countCharacters());
            System.out.printf("Syllables: %d%n", textProperties.countSyllables());
            System.out.printf("Polysyllables: %d%n", textProperties.countPolysyllables());

            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            String algorithm = scanner.nextLine();

            System.out.println();
            switch (algorithm) {
                case "ARI" -> Printer.printAriResults(textProperties);
                case "FK" -> Printer.printFkResults(textProperties);
                case "SMOG" -> Printer.printSmogResults(textProperties);
                case "CL" -> Printer.printClResults(textProperties);
                case "all" -> {
                    Printer.printAriResults(textProperties);
                    Printer.printFkResults(textProperties);
                    Printer.printSmogResults(textProperties);
                    Printer.printClResults(textProperties);
                }
                default -> System.out.println("unknown option");
            }
            Printer.printAverageAge();
        }
    }
}
