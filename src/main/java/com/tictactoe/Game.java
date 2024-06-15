package com.tictactoe;

import java.util.Arrays;

public class Game {

    private final char[] board;

    public Game() {
        this.board = new char[9];
        Arrays.fill(this.board, ' ');
    }

    public void putOneMarkOnGame(int index, char symbol) {
        if (symbol == 'X' || symbol == 'O') {
            this.board[index] = symbol;
        }
    }

    public boolean isEmptyPosition(int index) {
        return this.board[index] == ' ';
    }

    public String printGameBoard() {
        return """
                ---------
                | %s %s %s |
                | %s %s %s |
                | %s %s %s |
                ---------""".formatted(this.board[0], this.board[1], this.board[2],
                this.board[3], this.board[4], this.board[5],
                this.board[6], this.board[7], this.board[8]);
    }

    public char[] getBoard() {
        return board;
    }
}
