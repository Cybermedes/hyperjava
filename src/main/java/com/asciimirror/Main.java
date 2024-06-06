package com.asciimirror;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("""
                    *You can find some text files templates at the folder on the github repo:
                    => src/main/resources/ascii_animals/
                    And then you can pass either the relative or absolute path of template to
                    the program*
                    """);
            System.out.println("Input the file path:");
            String userInput = scanner.nextLine();

            Path filePath = Path.of(userInput);
            if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                Optional<Integer> max = lines.stream()
                        .map(String::length)
                        .max(Integer::compareTo);

                if (max.isPresent()) {
                    int longest = max.get();
                    for (String line : lines) {
                        System.out.printf("%-" + longest + "s || %" + longest + "s%n", line, mirrorLine(line));
                    }
                }

            } else {
                System.out.println("File not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String mirrorLine(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = line.length() -1 ; i >= 0; i--) {
            sb.append(mirroCharacter(line.charAt(i)));
        }
        return sb.toString();
    }

    private static char mirroCharacter(char c) {
        return switch (c) {
            case '/' -> '\\';
            case '\\' -> '/';
            case '(' -> ')';
            case ')' -> '(';
            case '[' -> ']';
            case ']' -> '[';
            case '<' -> '>';
            case '>' -> '<';
            default -> c;
        };
    }
}
