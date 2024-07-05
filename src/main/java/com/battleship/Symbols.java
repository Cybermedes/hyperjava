package com.battleship;

enum Symbols {

    FOG('~');

    private final char symbol;

    Symbols(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
