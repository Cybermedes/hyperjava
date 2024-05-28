package com.tictactoe;

import java.util.Scanner;

class TicTacToe {

    private static char[] flags;

    void startGame() {

        flags = new char[9];
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            int numMoves = 0;

            for (int i = 0; i <= 8; i++) {
                flags[i] = input.charAt(numMoves);
                numMoves++;
            }

            System.out.println("---------");
            System.out.printf("| %s %s %s |\n", flags[0], flags[1], flags[2]);
            System.out.printf("| %s %s %s |\n", flags[3], flags[4], flags[5]);
            System.out.printf("| %s %s %s |\n", flags[6], flags[7], flags[8]);
            System.out.println("---------");

            System.out.println(checkWinner());
        }
    }

    private static String checkWinner() {

        int countO = 0, countX = 0;
        for (int i = 0; i < 9; i++) {
            if (flags[i] == 'X') {
                countX++;
            } else if (flags[i] == 'O') {
                countO++;
            }
        }
        boolean matchO = false, matchX = false;
        for (int a = 0; a < 8; a++) {
            int line = switch (a) {
                case 0 -> flags[0] + flags[1] + flags[2];
                case 1 -> flags[3] + flags[4] + flags[5];
                case 2 -> flags[6] + flags[7] + flags[8];
                case 3 -> flags[0] + flags[3] + flags[6];
                case 4 -> flags[1] + flags[4] + flags[7];
                case 5 -> flags[2] + flags[5] + flags[8];
                case 6 -> flags[0] + flags[4] + flags[8];
                case 7 -> flags[2] + flags[4] + flags[6];
                default -> 0;
            };
            if (line == 264) {
                matchX = true;
            }
            if (line == 237) {
                matchO = true;
            }
        }
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
}
