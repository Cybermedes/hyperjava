package com.battleship;

class TooCloseProximityException extends Exception { }

class InvalidSizeException extends Exception { }

class InvalidLocationException extends Exception { }

record Shift(int x, int y) { }

record Coordinate(int axisX, int axisY) {

    static Coordinate parseCoordinate(String position) throws InvalidLocationException {
        int x = position.endsWith("10") ? 9 : position.charAt(1) - 49;
        int y = position.charAt(0) - 65;

        if (x > 9 || y < 0 || y > 9 || (position.length() == 3 && !position.endsWith("10"))) {
            throw new InvalidLocationException();
        }
        return new Coordinate(x, y);
    }
}

class Position {

    private final Coordinate start;
    private final Coordinate end;

    Position(String start, String end) throws InvalidLocationException {
        Coordinate firstCoordinate = Coordinate.parseCoordinate(start);
        Coordinate secondCoordinate = Coordinate.parseCoordinate(end);

        if (firstCoordinate.axisX() < secondCoordinate.axisX() ||
                firstCoordinate.axisY() < secondCoordinate.axisY()) {
            this.start = firstCoordinate;
            this.end = secondCoordinate;
        } else {
            this.start = secondCoordinate;
            this.end = firstCoordinate;
        }
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}

record Ship(String name, int size) { }
