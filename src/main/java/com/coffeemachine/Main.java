package com.coffeemachine;

import java.util.Scanner;

public class Main {

    private static final int[] ingredients = {400, 540, 120, 9, 550};
    private static boolean turnOnMachine = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(">> Write action (buy, fill, take, remaining, exit):");
            String userOption = scanner.next();

            switch (userOption) {
                case "buy" -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte," +
                            " 3 - cappuccino, back - to main menu:");
                    String coffeeOptions = scanner.next();
                    if (!coffeeOptions.equals("back")) {
                        CoffeeMachine.makeCoffee(Integer.parseInt(coffeeOptions), ingredients);
                    }
                }
                case "fill" -> {
                    System.out.println("Write how many ml of water you want to add:");
                    int moreWater = scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    int moreMilk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int moreBeans = scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add:");
                    int moreCups = scanner.nextInt();
                    CoffeeMachine.fillMachine(ingredients, moreWater, moreMilk, moreBeans, moreCups);
                    scanner.nextLine();
                    }
                case "take" -> CoffeeMachine.takeMoney(ingredients);
                case "remaining" -> CoffeeMachine.checkInventory(ingredients);
                case "exit" -> turnOnMachine = false;
                default -> System.out.println("Write buy, fill, take, remaining or exit!");
            }
        } while (turnOnMachine);
        scanner.close();
    }
}
