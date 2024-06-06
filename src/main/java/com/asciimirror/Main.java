package com.asciimirror;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input the file path:");
            String filePath = scanner.nextLine();

            try (InputStream is = Main.class.getClassLoader().getResourceAsStream(filePath);
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.printf("%-16s | %16s%n", line, mirrorLine(line));
                }

            }
        } catch (IOException | NullPointerException e) {
            System.out.println("File not found!");
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
            default -> c;
        };
    }
}
