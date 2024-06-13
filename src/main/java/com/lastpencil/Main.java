package com.lastpencil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initial setup
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("How many pencils would you like to use:");
            int numberOfPencils;

            // Loop until user sets a valid number of pencils input
            while (true) {
                try {
                    numberOfPencils = Integer.parseInt(scanner.nextLine());
                    if (numberOfPencils <= 0) {
                        System.out.println("The number of pencils should be positive");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The number of pencils should be numeric");
                }
            }

            // Set who will the first player and loop until a valid input name
            System.out.println("Who will be the first (John, Jack):");
            String player;
            while (true) {
                player = scanner.nextLine();
                if (player.equals("John") || player.equals("Jack")) {
                    break;
                } else {
                    System.out.println("Choose between 'John' and 'Jack'");
                }
            }
            String otherPlayer = player.equals("John") ? "Jack" : "John";

            // Initialize some variables to be used inside the loop
            int numberOfPencilsTaken = 0;
            int roundNumber = 2; // variable to help tracking who is the current player
            String currentPlayer = "";

            while (numberOfPencils >= 1) {
                System.out.println("|".repeat(numberOfPencils));
                currentPlayer = roundNumber % 2 == 0 ? player : otherPlayer;
                System.out.printf("%s's turn!%n", currentPlayer);

                // Loop until a valid input value
                boolean flag = true;
                while (flag) {
                    try {
                        numberOfPencilsTaken = Integer.parseInt(scanner.nextLine());
                        if (numberOfPencilsTaken <= 0 || numberOfPencilsTaken >= 4) {
                            throw new NumberFormatException();
                        } else if (numberOfPencilsTaken > numberOfPencils) {
                            System.out.println("Too many pencils were taken");
                        } else {
                            flag = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }
                }

                // Reduce the number of pencils in the table and increase round number
                numberOfPencils -= numberOfPencilsTaken;
                roundNumber++;
            }

            System.out.printf("%s won!%n", currentPlayer.equals("John") ? "Jack" : "John");
        }
    }
}

