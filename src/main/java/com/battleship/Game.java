package com.battleship;

import java.util.Scanner;

class Game {

    private final Board board;

    Game() {
        this.board = new Board();
    }

    void start() {
        Printer.printSetUpBoard(board);
        readPosition();
    }

    void readPosition() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the coordinates of the ship:");
            String start = scanner.next();
            String end = scanner.next();
            System.out.println(validateCoordinates(start, end));
        }
    }

    String validateCoordinates(String start, String end) {
        // Check if the input format is valid
        if (!isValidCoordinate(start) || !isValidCoordinate(end)) {
            return "Error!";
        }

        // Convert the coordinates into integers, ASCII values for the chars
        int startRow = start.toUpperCase().charAt(0) - 'A';
        int startCol = Integer.parseInt(start.substring(1)) - 1;
        int endRow = end.toUpperCase().charAt(0) - 'A';
        int endCol = Integer.parseInt(end.substring(1)) - 1;

        // Check if the coordinates are in a straight line (same row or same column)
        if (startRow != endRow && startCol != endCol) {
            return "Error!";
        }

        // Generate the parts and calculate the length
        StringBuilder parts = new StringBuilder();
        int length;
        if (startRow == endRow) {
            length = Math.abs(endCol - startCol) + 1;
            for (int col = Math.min(startCol, endCol); col <= Math.max(startCol, endCol); col++) {
                parts.append((char) (startRow + 'A')).append(col + 1).append(" ");
            }
        } else {
            length = Math.abs(endRow - startRow) + 1;
            for (int row = Math.min(startRow, endRow); row <= Math.max(startRow, endRow); row++) {
                parts.append((char) (row + 'A')).append(startCol + 1).append(" ");
            }
        }
        return "Length: " + length + System.lineSeparator() + "Parts: " + parts.toString().trim();
    }

    private static boolean isValidCoordinate(String coordinate) {
        if (coordinate == null || coordinate.length() < 2 || coordinate.length() > 3) {
            return false;
        }
        char row = coordinate.charAt(0);
        String colStr = coordinate.substring(1);
        if (row < 'A' || row > 'J') {
            return false;
        }
        try {
            int col = Integer.parseInt(colStr);
            if (col < 1 || col > 10) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
