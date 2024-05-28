package com.cinemaroom;

import java.util.InputMismatchException;
import java.util.Scanner;

class Cinema {

    private final static String VACANT_SEAT = "\033[32;1mS\033[0m";
    private final static String OCCUPIED_SEAT = "\033[31;1mB\033[0m";
    private static int rowNumber = 0;
    private static int seatNumber = 0;
    private static int ticketsSold = 0, income = 0;
    private static boolean purchaseConfirmation = false;
    private static String[][] cinema;

    void openCinema() {
        Scanner scanner = new Scanner(System.in);

        printIntro();
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row: ");
        int seats = scanner.nextInt();

        cinema = new String[rows + 1][seats + 1];
        createCinema();

        do {
            System.out.println("""
                    -------------------
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    -------------------""");
            System.out.print(">> ");
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1 -> printCinemaMap(cinema);
                case 2 -> {
                    do {
                        try {
                            System.out.print("Enter a row number: ");
                            rowNumber = scanner.nextInt();
                            System.out.print("Enter a seat number in that row: ");
                            seatNumber = scanner.nextInt();
                            purchaseConfirmation = true;
                        } catch (NumberFormatException | InputMismatchException e) {
                            System.out.println("Wrong input! Try it again...");
                            scanner.next();
                        }
                    } while (!purchaseConfirmation);
                    loadBusySeats(rowNumber, seatNumber);
                    checkPrice(rows, seats, rowNumber);
                }
                case 3 -> calculateProfit(rows, seats, ticketsSold);
                case 0 -> {
                    scanner.close();
                    System.exit(0);
                }
            }
        } while (true);
    }

    private static void createCinema() {
        for (int i = 0; i < cinema.length; ++i) {
            for (int j = 0; j < cinema[i].length; ++j) {
                if (i > 0 && j > 0) {
                    cinema[i][j] = VACANT_SEAT;
                } else if (i == 0 && j > 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (i > 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = " ";
                }
            }
        }
    }

    private static void printCinemaMap(String[][] seatsMap) {
        System.out.println("\nCinema:");
        for (String[] symbols : seatsMap) {
            for (String symbol : symbols) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    private static void loadBusySeats(int row, int col) {
        if (cinema[row][col].equals(VACANT_SEAT)) {
            cinema[row][col] = OCCUPIED_SEAT;
            ticketsSold++;
        } else {
            System.out.println("That ticket has already been purchased!");
            purchaseConfirmation = false;
        }
    }

    private static void checkPrice(int rows, int seats, int rowNumber) {
        if (purchaseConfirmation) {
            if (rows * seats < 60) {
                System.out.println("TICKET PURCHASED!\nTicket price: $10");
                income += 10;
            } else {
                if (rowNumber <= rows / 2) {
                    System.out.println("TICKET PURCHASED!\nTicket price: $10");
                    income += 10;
                } else {
                    System.out.println("TICKET PURCHASED!\nTicket price: $8");
                    income += 8;
                }
            }
        }
    }

    private static void calculateProfit(int rows, int seats, int ticketsSold) {
        int maximumRevenue;
        double ticketsSoldPercent = (double) (ticketsSold * 100) / (rows * seats);
        System.out.printf("Number of purchased tickets: %d\n", ticketsSold);
        System.out.printf("Occupancy: %.2f%%\n", ticketsSoldPercent);
        System.out.printf("Current income: $%d\n", income);
        if (rows * seats < 60) {
            System.out.print("Maximum revenue: ");
            System.out.println("$" + rows * seats * 10);
        } else {
            System.out.print("Maximum revenue: ");
            if (rows % 2 == 0) {
                maximumRevenue = 8 * (rows / 2) * seats + 10 * (rows / 2) * seats;
                System.out.println("$" + maximumRevenue);
            } else {
                maximumRevenue = 8 * (rows / 2 + 1) * seats + 10 * (rows / 2) * seats;
                System.out.println("$" + maximumRevenue);
            }
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static void printIntro() {
        System.out.println("-----------Welcome to the CINEJAVA-------------");
        System.out.println("First create your movie theater room by typing the dimensions");
        System.out.println("""
                Then use the menu options by typing the number to check the:
                (1) the seats layout. S is for available seat and B is occupied seat
                (2) to buy a ticket and let the user choose his seat
                If there are less than 60 seats, the tickets will cost 10 dollars;
                If there are 60 or more seats, the tickets for the first half rows
                will cost 10 dollars, and 8 dollars for the second half.
                If the number of rows is odd, round up for the second half.
                Example: 9 rows => first half = 4 & second half = 5
                (3) show the economical statistics at live time of your business
                (0) to exit the program and close operations.
                -----------------------------------------------
                """);
    }
}
