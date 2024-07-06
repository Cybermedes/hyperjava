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
    private int numberOfPossibleHits;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.numberOfPossibleHits = 0;
    }

    void start() {
        // Initial preparations
        Printer.printSetUpBoard(board);
        readShipPosition();

        // Start battle
        System.out.println("The game starts!");
        System.out.println();
        Printer.setFogOfWar();
        Printer.printSetUpBoard(board);

        System.out.println("Take a shot!");
        System.out.println();


        while (numberOfPossibleHits > 0) {
            fire();
        }

        scanner.close();
    }

    void fire() {

        Coordinate shot;
        while (true) {
            try {
                String position = scanner.next().toUpperCase();
                if (isValidPosition(position)) {
                    shot = Coordinate.parseCoordinate(position);
                    break;
                } else {
                    throw new InvalidLocationException();
                }
            } catch (InvalidLocationException e) {
                System.out.printf("%nError! You entered the wrong coordinates! Try again:%n%n");
            }
        }

        System.out.println();
        if (board.getBoard()[shot.axisY()][shot.axisX()] == 'O') {
            board.updateBoard(shot.axisX(), shot.axisY(), 'X');
            numberOfPossibleHits--;
            Printer.printSetUpBoard(board);

            if (isShipStillAfloat(shot)) {
                System.out.println("You hit a ship! Try again:");
            } else if (numberOfPossibleHits > 0) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.print("You sank the last ship. You won. Congratulations!");
            }

        } else {
            if (board.getBoard()[shot.axisY()][shot.axisX()] != 'X') {
                board.updateBoard(shot.axisX(), shot.axisY(), 'M');
            }
            Printer.printSetUpBoard(board);
            System.out.println("You missed! Try again:");
        }
        System.out.println();
    }

    void readShipPosition() {

        for (Ship ship : this.ships) {
            numberOfPossibleHits += ship.size();
            System.out.printf("Enter the coordinates of the %s (%d cells):%n%n", ship.name(), ship.size());

            while (true) {
                try {
                    placeWarship(ship.size());
                    break;
                } catch (InvalidSizeException e) {
                    System.out.printf("%nError! Wrong length of the %s! Try again:%n", ship.name());
                } catch (InvalidLocationException e) {
                    System.out.println();
                    System.out.println("Error! Wrong ship location! Try again:");
                } catch (TooCloseProximityException e) {
                    System.out.println();
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }
                System.out.println();
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
        if (!isValidPosition(coordinateShipStart) || !isValidPosition(coordinateShipEnd)) {
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

    private static boolean isValidPosition(String position) {
        if (position == null || position.length() < 2 || position.length() > 3) {
            return false;
        }
        char row = position.charAt(0);
        String colStr = position.substring(1);
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
                if (board.getBoard()[y + shift.y()][x + shift.x()] == 'O') {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return false;
    }

    private boolean isShipStillAfloat(Coordinate coordinate) {
        return isBordering(coordinate.axisX(), coordinate.axisY());
    }
}
