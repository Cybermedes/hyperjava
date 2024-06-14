package com.lastpencil;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Bot {

    private final int[] losingPositions;
    private final int[] winningPositionOnePencil;
    private final int[] winningPositionTwoPencils;
    private final int[] winningPositionThreePencils;

    public Bot() {

        this.losingPositions = Stream.iterate(5, x -> x + 4)
                .limit(50)
                .mapToInt(Integer::intValue)
                .toArray();

        this.winningPositionOnePencil = Stream.iterate(2, x -> x + 4)
                .limit(50)
                .mapToInt(Integer::intValue)
                .toArray();

        this.winningPositionTwoPencils = Stream.iterate(3, x -> x + 4)
                .limit(50)
                .mapToInt(Integer::intValue)
                .toArray();

        this.winningPositionThreePencils = Stream.iterate(4, x -> x + 4)
                .limit(50)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    protected int takePencils(int pencilsOnTable) {
        Random random = new Random();
        if (Arrays.stream(losingPositions).anyMatch(x -> x == pencilsOnTable)) {
            return random.nextInt(1,4);
        } else if (Arrays.stream(winningPositionOnePencil).anyMatch(x -> x == pencilsOnTable)) {
            return 1;
        } else if (Arrays.stream(winningPositionTwoPencils).anyMatch(x -> x == pencilsOnTable)) {
            return 2;
        } else if (Arrays.stream(winningPositionThreePencils).anyMatch(x -> x == pencilsOnTable)){
            return 3;
        } else {
            return 1;
        }
    }
}
