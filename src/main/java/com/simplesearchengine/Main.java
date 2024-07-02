package com.simplesearchengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final List<String> SAVED_DATA = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            getInitialUserPrompts(scanner);

            while (true) {
                printMenuOption();
                int option = scanner.nextInt();
                scanner.nextLine(); // clean the escape character
                switch (option) {
                    case 1 -> {
                        System.out.println();
                        System.out.println("Enter a name or email to search all suitable people.");
                        String searchWord = scanner.nextLine();
                        System.out.print(searchForData(searchWord));
                    }
                    case 2 -> printAllData();
                    case 0 -> {
                        System.out.printf("%nBye!");
                        System.exit(0);
                    }
                    default -> System.out.printf("%nIncorrect option! Try again.%n");
                }
            }
        }
    }

    private static void printAllData() {
        System.out.println();
        System.out.println("=== List of people ===");
        SAVED_DATA.forEach(System.out::println);
    }

    public static void printMenuOption() {
        System.out.println();
        String menu = """
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""";
        System.out.println(menu);
    }

    public static void getInitialUserPrompts(Scanner scanner) {
        System.out.println("Enter the number of people:");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter all people:");
        for (int i = 0; i < numberOfPeople; i++) {
            SAVED_DATA.add(scanner.nextLine());
        }
    }

    public static String searchForData(String searchWord) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);

        for (String savedDatum : SAVED_DATA) {
            Matcher matcher = pattern.matcher(savedDatum);
            if (matcher.find()) {
                sb.append(savedDatum).append(System.lineSeparator());
            }
        }
        if (sb.isEmpty()) {
            sb.append("No matching people found.").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
