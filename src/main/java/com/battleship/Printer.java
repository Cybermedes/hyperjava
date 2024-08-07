package com.battleship;

abstract class Printer {

    private static boolean fogOfWar = false;

    static void setFogOfWar() {
        fogOfWar = true;
    }

    static void printSetUpBoard(Board board) {
        // Print the first row with numbers
        System.out.print(" ");
        for (int i = 1; i < 11; i++) System.out.print(" " + i);
        System.out.println();

        char[][] gameBoard = board.getBoard();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    System.out.print((char) ('A' + i)); // ASCII of 'A' is 65
                } else {
                    if (fogOfWar && gameBoard[i][j - 1] == 'O') {
                        System.out.print(" " + Symbols.FOG.getSymbol());
                    } else {
                        System.out.print(" " + gameBoard[i][j - 1]);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
