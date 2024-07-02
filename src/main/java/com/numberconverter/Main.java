package com.numberconverter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String userPrompt;
        int sourceBase;
        int targetBase;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
                userPrompt = scanner.nextLine();
                if (userPrompt.equals("/exit")) {
                    System.exit(0);
                } else {
                    sourceBase = Integer.parseInt(userPrompt.split("\\s+")[0]);
                    targetBase = Integer.parseInt(userPrompt.split("\\s+")[1]);
                    convert(sourceBase, targetBase, scanner);
                }
                System.out.println();
            }
        }
    }

    protected static void convert(int sourceBase, int targetBase, Scanner scanner) {
        if (37 < sourceBase || sourceBase < 2 || targetBase < 2 || targetBase > 37) {
            return;
        }
        String input;
        while (true) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ",
                    sourceBase,
                    targetBase);
            input = scanner.nextLine();
            if (input.equals("/back")) {
                return;
            }

            System.out.println("Conversion result: " + new BigInteger(input, sourceBase)
                    .toString(targetBase));
            System.out.println();
        }
    }

}
