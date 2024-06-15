package com.tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize an empty game board
        Game game = new Game();
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println(game.printGameBoard());
            int position;
            int symbolCounter = 1;
            do {
                position = getUserInput(scanner);
                if (game.isEmptyPosition(position)) {
                    char player = symbolCounter % 2 != 0 ? 'X' : 'O';
                    game.putOneMarkOnGame(position, player);
                    System.out.println(game.printGameBoard());
                    symbolCounter++;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } while (checkWinner(game.getBoard()).equals("Game not finished"));

            System.out.println(checkWinner(game.getBoard()));
        }
    }

    protected static String checkWinner(char[] board) {

        // Count the number of 'X' and 'O'
        int countO = 0, countX = 0;
        for (char element : board) {
            if (element == 'X') {
                countX++;
            } else if (element == 'O') {
                countO++;
            }
        }

        // Check if any 'O' or 'X' match in one of 8 possible ways
        boolean matchO = checkAnyMatch(board, 'O');
        boolean matchX = checkAnyMatch(board, 'X');

        // Check game results
        if (!matchX && !matchO && (countO + countX == 9)) {
            return "Draw";
        } else if ((countO - countX > 1) || (countX - countO > 1)) {
            return "Impossible";
        } else if (matchO & matchX & (countO + countX < 9)) {
            return "Impossible";
        } else if (matchX) {
            return "X wins";
        } else if (matchO) {
            return "O wins";
        }
        return "Game not finished";
    }

    protected static boolean checkAnyMatch(char[] game, char symbol) {
        return game[0] == symbol && game[1] == symbol && game[2] == symbol
                || game[3] == symbol && game[4] == symbol && game[5] == symbol
                || game[6] == symbol && game[7] == symbol && game[8] == symbol
                || game[0] == symbol && game[3] == symbol && game[6] == symbol
                || game[1] == symbol && game[4] == symbol && game[7] == symbol
                || game[2] == symbol && game[5] == symbol && game[8] == symbol
                || game[0] == symbol && game[4] == symbol && game[8] == symbol
                || game[2] == symbol && game[4] == symbol && game[6] == symbol;
    }

    protected static int getUserInput(Scanner scanner) {
        while (true) {
            try {
                String[] coordinates = scanner.nextLine().split(" ");

                if ((Integer.parseInt(coordinates[0]) <= 0 || Integer.parseInt(coordinates[0]) >= 4)
                        || (Integer.parseInt(coordinates[1]) <= 0 || Integer.parseInt(coordinates[1]) >= 4)) {
                    throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
                }

                return switch (String.join("", coordinates)) {
                    case "11" -> 0;
                    case "12" -> 1;
                    case "13" -> 2;
                    case "21" -> 3;
                    case "22" -> 4;
                    case "23" -> 5;
                    case "31" -> 6;
                    case "32" -> 7;
                    default -> 8;
                };

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            } catch (IllegalArgumentException iea) {
                System.out.println(iea.getMessage());
            }
        }
    }
}
