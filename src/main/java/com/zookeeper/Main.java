package com.zookeeper;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Animal[] animals = {
                Animal.CAMEL,
                Animal.LION,
                Animal.DEER,
                Animal.GOOSE,
                Animal.BAT,
                Animal.RABBIT
        };

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Please enter the number of the habitat (1 to 5) " +
                        "you would like to view or type 'exit' to exit: ");
                String input = scanner.nextLine();
                if (input.equals("exit")) {
                    System.out.println("See you later!");
                    break;
                } else {
                    System.out.println(animals[Integer.parseInt(input)].getAnimal());
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("invalid input, exiting the program");
        }
    }
}
