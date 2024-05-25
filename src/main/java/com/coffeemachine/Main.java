package com.coffeemachine;

import java.util.Scanner;

public class Main {

    private static final int[] ingredients = {400, 540, 120, 9, 550};
    private static boolean turnOnMachine = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String userOption = scanner.next();

            switch (userOption) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte," +
                            " 3 - cappuccino, back - to main menu:");
                    String coffeeOptions = scanner.next();
                    if (coffeeOptions.equals("back")) {
                        break;
                    } else {
                        makeCoffee(Integer.parseInt(coffeeOptions), ingredients);
                        break;
                    }
                case "fill":
                    System.out.println("Write how many ml of water you want to add:");
                    int moreWater = scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    int moreMilk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int moreBeans = scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add:");
                    int moreCups = scanner.nextInt();
                    fillMachine(ingredients, moreWater, moreMilk, moreBeans, moreCups);
                    scanner.nextLine();
                    break;
                case "take":
                    takeMoney(ingredients);
                    break;
                case "remaining":
                    checkInventory(ingredients);
                    break;
                case "exit":
                    turnOnMachine = false;
                    break;
                default:
                    System.out.println("Write buy, fill, take, remaining or exit!");
                    break;
            }
        } while (turnOnMachine);
        scanner.close();
    }

    public static void checkInventory(int[] inventory) {
        System.out.printf("The coffee machine has:%n" +
                        "%d ml of water%n" +
                        "%d ml of milk%n" +
                        "%d g of coffee beans%n" +
                        "%d disposable cups%n" +
                        "$%d of money%n",
                inventory[0], inventory[1], inventory[2],
                inventory[3], inventory[4]
        );
    }

    public static void makeCoffee(int options, int[] ingredients) {
        switch (options) {
            case 1:
                //Espresso
                if (ingredients[0] >= 250 && ingredients[2] >= 16 && ingredients[3] >= 1) {
                    System.out.println("I have enough resources, making you a coffee!%n");
                    ingredients[0] -= 250;
                    ingredients[2] -= 16;
                    ingredients[3] -= 1;
                    ingredients[4] += 4;
                    break;
                } else if (ingredients[0] < 250) {
                    System.out.println("Sorry, not enough water!%n");
                    break;
                } else if (ingredients[2] < 16) {
                    System.out.println("Sorry, not enough beans!%n");
                    break;
                } else {
                    System.out.println("Sorry, not enough cups!%n");
                    break;
                }
            case 2:
                //Latte
                if (ingredients[0] >= 350 && ingredients[1] >= 75 &&
                        ingredients[2] >= 20 && ingredients[3] >= 1) {
                    System.out.println("I have enough resources, making you a coffee!%n");
                    ingredients[0] -= 350;
                    ingredients[1] -= 75;
                    ingredients[2] -= 20;
                    ingredients[3] -= 1;
                    ingredients[4] += 7;
                    break;
                } else if (ingredients[0] < 350) {
                    System.out.println("Sorry, not enough water!%n");
                    break;
                } else if (ingredients[1] < 75) {
                    System.out.println("Sorry, not enough milk!%n");
                    break;
                } else if (ingredients[2] < 20) {
                    System.out.println("Sorry, not enough beans!%n");
                    break;
                } else {
                    System.out.println("Sorry, not enough cups!%n");
                    break;
                }
            case 3:
                //Capuccino
                if (ingredients[0] >= 200 && ingredients[1] >= 100 &&
                        ingredients[2] >= 12 && ingredients[3] >= 1) {
                    System.out.println("I have enough resources, making you a coffee!%n");
                    ingredients[0] -= 200;
                    ingredients[1] -= 100;
                    ingredients[2] -= 12;
                    ingredients[3] -= 1;
                    ingredients[4] += 6;
                    break;
                } else if (ingredients[0] < 200) {
                    System.out.println("Sorry, not enough water!%n");
                    break;
                } else if (ingredients[1] < 100) {
                    System.out.println("Sorry, not enough milk!%n");
                    break;
                } else if (ingredients[2] < 12) {
                    System.out.println("Sorry, not enough beans!%n");
                    break;
                } else {
                    System.out.println("Sorry, not enough cups!%n");
                    break;
                }
        }
    }

    public static void fillMachine(int[] stock, int water, int milk, int beans, int cups) {
        stock[0] += water;
        stock[1] += milk;
        stock[2] += beans;
        stock[3] += cups;
    }

    public static void takeMoney(int[] cash) {
        System.out.println("I gave you $" + cash[4] + "%n");
        cash[4] = 0;
    }
}