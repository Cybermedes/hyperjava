package com.lastpencil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.runGame();
    }

    protected void runGame() {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfPencils = getNumberOfPencils(scanner);
            String firstPlayer = getFirstPlayer(scanner);
            String otherPlayer = firstPlayer.equals("John") ? "Jack" : "John";

            Bot jack = new Bot();
            playGame(scanner, numberOfPencils, firstPlayer, otherPlayer, jack);
        }
    }

    protected int getNumberOfPencils(Scanner scanner) {
        System.out.println("How many pencils would you like to use:");
        int numberOfPencils;
        while (true) {
            try {
                numberOfPencils = Integer.parseInt(scanner.nextLine());
                if (numberOfPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    return numberOfPencils;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
    }

    protected String getFirstPlayer(Scanner scanner) {
        System.out.println("Who will be the first (John, Jack):");
        String player;
        while (true) {
            player = scanner.nextLine();
            if (player.equals("John") || player.equals("Jack")) {
                return player;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        }
    }

    protected void playGame(Scanner scanner,
                          int numberOfPencils,
                          String firstPlayer,
                          String otherPlayer,
                          Bot bot) {
        int numberOfPencilsTaken;
        int roundNumber = 2;
        String currentPlayer = "";

        while (numberOfPencils >= 1) {
            System.out.println("|".repeat(numberOfPencils));
            currentPlayer = roundNumber % 2 == 0 ? firstPlayer : otherPlayer;
            System.out.printf("%s's turn!%n", currentPlayer);

            numberOfPencilsTaken = getPencilsTaken(scanner, numberOfPencils, currentPlayer, bot);
            numberOfPencils -= numberOfPencilsTaken;
            roundNumber++;
        }

        System.out.printf("%s won!%n", currentPlayer.equals("John") ? "Jack" : "John");
    }

    protected int getPencilsTaken(Scanner scanner,
                                int numberOfPencils,
                                String currentPlayer,
                                Bot bot) {
        int numberOfPencilsTaken;
        while (true) {
            try {
                if (currentPlayer.equals("Jack")) {
                    numberOfPencilsTaken = bot.takePencils(numberOfPencils);
                    System.out.println(numberOfPencilsTaken);
                } else {
                    numberOfPencilsTaken = Integer.parseInt(scanner.nextLine());
                }

                if (numberOfPencilsTaken <= 0 || numberOfPencilsTaken >= 4) {
                    throw new NumberFormatException();
                } else if (numberOfPencilsTaken > numberOfPencils) {
                    System.out.println("Too many pencils were taken");
                } else {
                    return numberOfPencilsTaken;
                }
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
    }
}
