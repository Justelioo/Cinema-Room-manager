package com.company;

import java.util.Scanner;

public class Main {
    static int boughtTickets = 0;
    static int curIncome = 0;


    public static void main(String[] args) {
        int[] rowsSeats = roomInfo();
        String[][] sit = emptyStrings(rowsSeats);
        choice(rowsSeats, sit);


    }

    // getting Input
    public static int getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    //all seats
    public static int all(int[] rowsSeats){
        return rowsSeats[0] * rowsSeats[1];
    }

    //getting starting info about room
    public static int[] roomInfo() {
        System.out.println("Enter the number of rows:");
        int rows = getInput();
        System.out.println("Enter the number of seats in each row:");
        int seats = getInput();
        System.out.println();
        return new int[]{rows, seats};

    }

    //getting empty seats. letters SSSSS
    public static String[][] emptyStrings(int[] rowsSeats){

        String[][] sit = new String[rowsSeats[0]][rowsSeats[1]];
        for (int i = 0; i < rowsSeats[0]; i++) {

            for (int j = 0; j < rowsSeats[1]; j++) {
                sit[i][j] = "S";
            }
        }
        return sit;
    }

    //asking what you want to do
    public static int whatToDo(){
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int ats = getInput();
        System.out.println();
        return ats;
    }


    //making decision on what you should do
    public static void choice(int[] rowsSeats, String[][] sit){

        while (true) {
            int whatToDo = whatToDo();

            if (whatToDo == 0) {
                break;
            }
            else if (whatToDo == 1) {
                room(rowsSeats, sit);
            }

            else if (whatToDo == 3) {
                statistics(rowsSeats);

            }

            else {
                buyTicket(sit, rowsSeats);

            }

        }
    }

    //choosing to see room nr 1
    public static void room(int[] rowsSeats, String[][] sit){
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= rowsSeats[1]; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rowsSeats[0]; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < rowsSeats[1]; j++) {

                System.out.print(sit[i][j] + " ");
            }
            System.out.println();


        }
        System.out.println();
    }

    //choosing to buy ticket nr 2
    public static void buyTicket(String[][] sit, int[] rowsSeats){
        while (true) {
            System.out.println("Enter a row number:");
            int rownr = getInput();
            System.out.println("Enter a seat number in that row:");
            int seatnr = getInput();
            if(rownr < 1 || seatnr < 0 || rownr > rowsSeats[0] || seatnr > rowsSeats[1]){
                System.out.println("Wrong input!\n");

            }
            else if (sit[rownr - 1][seatnr - 1] == "B") {
                System.out.println("That ticket has already been purchased!\n");
            }
            else {
                sit[rownr - 1][seatnr - 1] = "B";
                boughtTickets++;
                System.out.print("\nTicket price: $");

                int puse = rowsSeats[0] / 2;
                if (all(rowsSeats) < 60) {
                    curIncome += 10;
                    System.out.println(10 + "\n");
                }
                else {
                    if (rownr <= puse) {
                        curIncome += 10;
                        System.out.print(10 + "\n");
                    } else {
                        curIncome += 8;
                        System.out.println(8 + "\n");
                    }
                }
                break;

            }
        }

    }

    //showing statistics nr3
    public static void statistics(int[] rowsSeats){
        double percentage = 100.0 / all(rowsSeats) * boughtTickets;
        System.out.println("Number of purchased tickets: " + boughtTickets);
        System.out.printf("Percentage: %.2f%% \n" , percentage);
        System.out.println("Current income: $" + curIncome);

        if(all(rowsSeats)<60){
            System.out.println("Total income: $" + all(rowsSeats)*10);
        }
        else{
            if(rowsSeats[0] % 2==0){
                System.out.print("Total income: $");
                System.out.println((rowsSeats[0] / 2 * rowsSeats[1] * 10) + (rowsSeats[0] / 2 * rowsSeats[1] * 8));
            }
            else{
                int rowy = rowsSeats[0]/2;
                System.out.print("Total income: $");
                System.out.println((rowy * rowsSeats[1] * 10) + ((rowy +1) * rowsSeats[1] *8));
            }
        }

    }

}
