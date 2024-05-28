package com.coffeemachine;

class CoffeeMachine {

    static void checkInventory(int[] inventory) {
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

    static void fillMachine(int[] stock, int water, int milk, int beans, int cups) {
        stock[0] += water;
        stock[1] += milk;
        stock[2] += beans;
        stock[3] += cups;
    }

    static void takeMoney(int[] cash) {
        System.out.printf("Withdrawn: $%d from the cash register%n", cash[4]);
        cash[4] = 0;
    }

    static void makeCoffee(int options, int[] ingredients) {
        switch (options) {
            //Espresso
            case 1 -> {
                if (ingredients[0] >= 250 && ingredients[2] >= 16 && ingredients[3] >= 1) {
                    System.out.println("I have enough resources, making you a coffee!");
                    ingredients[0] -= 250;
                    ingredients[2] -= 16;
                    ingredients[3] -= 1;
                    ingredients[4] += 4;
                } else if (ingredients[0] < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if (ingredients[2] < 16) {
                    System.out.println("Sorry, not enough beans!");
                } else {
                    System.out.println("Sorry, not enough cups!");
                }
            }
            //Latte
            case 2 -> {
                if (ingredients[0] >= 350 && ingredients[1] >= 75 &&
                        ingredients[2] >= 20 && ingredients[3] >= 1) {
                    System.out.println("I have enough resources, making you a coffee!");
                    ingredients[0] -= 350;
                    ingredients[1] -= 75;
                    ingredients[2] -= 20;
                    ingredients[3] -= 1;
                    ingredients[4] += 7;
                } else if (ingredients[0] < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if (ingredients[1] < 75) {
                    System.out.println("Sorry, not enough milk!");
                } else if (ingredients[2] < 20) {
                    System.out.println("Sorry, not enough beans!");
                } else {
                    System.out.println("Sorry, not enough cups!");
                }
            }
            // Cappuccino
            case 3 -> {
                if (ingredients[0] >= 200 && ingredients[1] >= 100 &&
                        ingredients[2] >= 12 && ingredients[3] >= 1) {
                    System.out.println("I have enough resources, making you a coffee!");
                    ingredients[0] -= 200;
                    ingredients[1] -= 100;
                    ingredients[2] -= 12;
                    ingredients[3] -= 1;
                    ingredients[4] += 6;
                } else if (ingredients[0] < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if (ingredients[1] < 100) {
                    System.out.println("Sorry, not enough milk!");
                } else if (ingredients[2] < 12) {
                    System.out.println("Sorry, not enough beans!");
                } else {
                    System.out.println("Sorry, not enough cups!");
                }
            }
            default -> System.out.println("Invalid number option");
        }
    }
}
