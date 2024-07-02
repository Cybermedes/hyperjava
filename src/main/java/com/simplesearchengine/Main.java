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
            getUserPrompts(scanner);
        }
    }

    public static void getUserPrompts(Scanner scanner) {
        System.out.println("Enter the number of people:");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter all people:");
        for (int i = 0; i < numberOfPeople; i++) {
            SAVED_DATA.add(scanner.nextLine());
        }

        System.out.println();
        System.out.println("Enter the number of search queries:");
        int numberOfSearches = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfSearches; i++) {
            System.out.println();
            System.out.println("Enter data to search people:");
            String dataToSearch = scanner.nextLine();

            System.out.println(searchForData(dataToSearch));
        }
    }

    public static String searchForData(String searchWord) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);

        for (String savedDatum : SAVED_DATA) {
            Matcher matcher = pattern.matcher(savedDatum);
            if (matcher.find()) {
                if (sb.isEmpty()) {
                    sb.append(System.lineSeparator()).append("Found people:");
                }
                sb.append(System.lineSeparator()).append(savedDatum);
            }
        }
        if (sb.isEmpty()) {
            sb.append("No matching people found.");
        }
        return sb.toString();
    }
}
