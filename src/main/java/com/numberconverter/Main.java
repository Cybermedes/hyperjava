package com.numberconverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
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

            String[] integerAndDecimal = input.split("\\.");
            BigInteger integerPart = new BigInteger(integerAndDecimal[0], sourceBase);
            String decimalPart = integerAndDecimal.length > 1 ?
                    convertFraction(integerAndDecimal[1],
                            sourceBase,
                            targetBase) : "";

            System.out.println("Conversion result: " + joinIntegerAndDecimal(integerPart.toString(targetBase),
                    Objects.requireNonNull(decimalPart)));
            System.out.println();
        }
    }

    public static String convertFraction(String sourceDecimal, int sourceBase, int targetBase) {
        char c;
        double decimalValue = 0.0;
        for (int i = 0; i < sourceDecimal.length(); i++) {
            c = sourceDecimal.charAt(i);
            decimalValue += Character.digit(c, sourceBase) / Math.pow(sourceBase, i + 1);
        }
        return decimalFractionalToBase(new BigDecimal(decimalValue), targetBase, 0);
    }

    public static String decimalFractionalToBase(BigDecimal fractionalDecimal,
                                                 int base,
                                                 int depth) {
        BigDecimal bigBase = BigDecimal.valueOf(base);
        BigDecimal decimal = fractionalDecimal.multiply(bigBase);
        BigDecimal result = decimal.setScale(0, RoundingMode.FLOOR);
        BigDecimal rest = decimal.subtract(result);

        String resultInTarget = result.unscaledValue().toString(base);

        return decimalFractionalToBase(rest,
                base,
                new StringBuilder().append(resultInTarget),
                depth,
                bigBase);
    }

    public static String decimalFractionalToBase(BigDecimal fractionalDecimal,
                                                 int base,
                                                 StringBuilder result,
                                                 int depth,
                                                 BigDecimal bigBase) {

        BigDecimal decimal = fractionalDecimal.multiply(bigBase);
        BigDecimal result2 = decimal.setScale(0, RoundingMode.FLOOR);
        BigDecimal fractionRest = decimal.subtract(result2);

        if (depth > 7) {
            return result.append(result2.unscaledValue().toString(base)).toString();
        }
        if (result2.stripTrailingZeros().equals(BigDecimal.ZERO) &&
                fractionRest.stripTrailingZeros().equals(BigDecimal.ZERO)) {
            return decimalFractionalToBase(fractionRest,
                    base,
                    result.append("0"),
                    depth + 1, bigBase);
        }
        return decimalFractionalToBase(fractionRest,
                base,
                result.append(result2.unscaledValue().toString(base)),
                depth + 1, bigBase);
    }

    protected static String joinIntegerAndDecimal(String integer, String decimal) {
        if (decimal.isEmpty()) {
            return integer;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(integer).append(".");
        for (int i = 0; i < 5; i++) {
            sb.append(decimal.charAt(i));
        }
        return sb.toString();
    }
}
