package com.readabilityscore;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String text = scanner.nextLine();
            String readability = text.length() <= 100 ? "EASY" : "HARD";
            System.out.println(readability);
        }
    }
}
