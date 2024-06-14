package com.lastpencil;

import java.util.Random;

public class Bot {

    protected int takePencils(int pencilsOnTable) {
        Random random = new Random();
        // If there is one pencil on table, the bot take it
        if (pencilsOnTable == 1) {
            return 1;
        }

        // Check if the number of pencils in the table belongs to one of the strategy sequences
        int remainder = pencilsOnTable % 4;
        return switch (remainder) {
            case 2 -> 1;
            case 3 -> 2;
            case 0 -> 3;
            default -> random.nextInt(1,4);
        };
    }
}
