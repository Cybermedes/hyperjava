package com.chatbot;

import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greet("Dynamo", 2024);
        remindName();
        guessAge();
        count();
        test();
        end();
        scanner.close();
    }

    private static void greet(String assistantName, int birthYear) {
        System.out.printf("Hello! My name is %s.%n", assistantName);
        System.out.printf("I was created in %d.%n", birthYear);
        System.out.println("Please, remind me your name.");
    }

    private static void remindName() {
        String name = scanner.nextLine();
        System.out.printf("What a great name you have, %s!%n", name);
    }

    private static void guessAge() {
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int rem3 = scanner.nextInt();
        int rem5 = scanner.nextInt();
        int rem7 = scanner.nextInt();
        int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;
        System.out.printf("Your age is %d; that's a good time to start programming!%n", age);
    }

    private static void count() {
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int num = scanner.nextInt();
        for (int i = 0; i <= num; i++) {
            System.out.printf("%d!%n", i);
        }
    }

    private static void test() {
        System.out.println("Let's test your programming knowledge.");
        System.out.println("""
                What is Java?
                1. A programming language
                2. A software
                3. A video game
                4. An IDE
                5. A virtual machine""");

        while (true) {
            int answer = scanner.nextInt();
            if (answer == 1) break;
            System.out.println("Please, try again");
        }
    }

    private static void end() {
        System.out.println("Congratulations, have a nice day!");
    }
}
