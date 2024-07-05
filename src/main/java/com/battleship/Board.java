package com.battleship;

import java.util.Arrays;

class Board {

    private final char[][] board;

    Board() {
        char[][] rows = new char[10][10];
        for (char[] row : rows) {
            Arrays.fill(row, Symbols.FOG.getSymbol());
        }

        this.board = rows;
    }

    char[][] getBoard() {
        return board;
    }
}
