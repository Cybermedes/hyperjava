package com.battleship;

import java.util.Scanner;

class Game {

    private final Scanner scanner;
    private final Board board = new Board();
    private final Ship[] ships = {
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    };

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    void start() {
        Printer.printSetUpBoard(board);
        readShipPosition();
    }

    void readShipPosition() {

        for (Ship ship : this.ships) {
            System.out.println();
            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.name(), ship.size());
            System.out.println();

            while (true) {
                try {
                    placeWarship(ship.size());
                    break;
                } catch (InvalidSizeException e) {
                    System.out.println();
                    System.out.printf("Error! Wrong length of the %s! Try again:%n", ship.name());
                } catch (InvalidLocationException e) {
                    System.out.println();
                    System.out.println("Error! Wrong ship location! Try again:");
                } catch (TooCloseProximityException e) {
                    System.out.println();
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }
                System.out.println();
                System.out.print("> ");
            }
            System.out.println();
            Printer.printSetUpBoard(board);
        }
    }

    void placeWarship(int size) throws InvalidLocationException,
            InvalidSizeException,
            TooCloseProximityException {

        String coordinateShipStart = scanner.next().toUpperCase();
        String coordinateShipEnd = scanner.next().toUpperCase();

        // Check if the input format is valid
        if (!isValidCoordinate(coordinateShipStart) || !isValidCoordinate(coordinateShipEnd)) {
            throw new InvalidLocationException();
        }

        Position position = new Position(coordinateShipStart, coordinateShipEnd);
        // Check if warships size is correct and there are no collisions
        if (!isSizeCorrect(position, size)) {
            throw new InvalidSizeException();
        }
        if (!areCollisions(position)) {
            throw new TooCloseProximityException();
        }

        int startX = position.getStart().axisX();
        int startY = position.getStart().axisY();
        int endX = position.getEnd().axisX();
        int endY = position.getEnd().axisY();

        // Update game board with the warships positions
        if (startX == endX) {
            for (int y = startY; y != endY + 1; y++) {
                board.updateBoard(startX, y, 'O');
            }
        } else {
            for (int x = startX; x != endX + 1; x++) {
                board.updateBoard(x, startY, 'O');
            }
        }
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

    private boolean isSizeCorrect(Position position, int size) {
        if (position.getStart().axisX() == position.getEnd().axisX()) {
            return size == position.getEnd().axisY() - position.getStart().axisY() + 1;
        } else if (position.getStart().axisY() == position.getEnd().axisY()) {
            return size == position.getEnd().axisX() - position.getStart().axisX() + 1;
        } else {
            return false;
        }
    }

    private boolean areCollisions(Position position) {
        int startX = position.getStart().axisX();
        int startY = position.getStart().axisY();
        int endX = position.getEnd().axisX();
        int endY = position.getEnd().axisY();

        if (startX == endX) {
            for (int y = startY; y != endY + 1; y++) {
                if (isBordering(startX, y)) {
                    return false;
                }
            }
        } else {
            for (int x = startX; x != endX + 1; x++) {
                if (isBordering(x, startY)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBordering(int x, int y) {
        Shift[] shifts = {
                new Shift(-1, 1),
                new Shift(0, 1),
                new Shift(1, 1),
                new Shift(1, 0),
                new Shift(1, -1),
                new Shift(0, -1),
                new Shift(-1, -1),
                new Shift(-1, 0)
        };

        for (Shift shift : shifts) {
            try {
                if (!(board.getBoard()[y + shift.y()][x + shift.x()] == '~')) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return false;
    }
}
