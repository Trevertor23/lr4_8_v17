package ua.bv.vacations;

import ua.bv.vacations.mysql.MysqlConnector;
import ua.bv.vacations.vacationitems.Vacations;
import ua.bv.vacations.files.Logs;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        Menu mainMenu = new Menu("Main Menu", new String[] {"Choose the Tours", "Your bookings", "Read logs",
                                            "How to use the program?", "Exit"});
        byte chosen = 0;
        Vacations vacs = new Vacations();
        Scanner inp = new Scanner(System.in);
        Logs logs = new Logs();

        System.out.println("-----Vacation Explorer ver 1.0-----");
        Thread.sleep(500);
        System.out.println("Author: Bohdan Velykokon (CS-11 s.p.)");
        Thread.sleep(500);
        logs.writeLog("Starting the program...");

        while(chosen != 5){
            chosen = mainMenu.show();
            logs.writeLog("Program started successfully!");

            switch (chosen){
                case 1:
                    int n;
                    String lt;
                    boolean orderPrice = false;

                    logs.writeLog("Choosing the tour...");
                    while(true) {
                        String[][] vacsArr;
                        if (orderPrice) {
                            vacsArr = vacs.getVacations("price");
                        } else {
                            vacsArr = vacs.getVacations();
                        }
                        System.out.println("\nChoose the vacation to see more details:");
                        for (int i = 0; i < vacsArr.length; i++) {
                            System.out.println((i + 1) + ". '" + vacsArr[i][1] + "', "+ vacsArr[i][4] + ", " + vacsArr[i][8] + "$");
                        }
                        if (!orderPrice)
                            System.out.println("\n99 - order vacations by price");
                        System.out.println("\n1-" + vacsArr.length + " - delete the corresponding booking, 0 - Return to menu");

                        n = inp.nextInt();
                        if(n == 99) {
                            orderPrice = true;
                            continue;
                        }
                        if(n == 0 || n >= vacsArr.length){
                            orderPrice = false;
                            break;
                        }
                        n = n - 1;
                        System.out.println("\n---Details---");
                        System.out.println("Name: " + vacsArr[n][1]);
                        System.out.println("Description: " + vacsArr[n][2]);
                        System.out.println("Transport: " + vacsArr[n][5]);
                        System.out.println("Days: " + vacsArr[n][6]);
                        System.out.println("Food: " + (vacsArr[n][7] == "0" ? "not included" : "may be included"));
                        System.out.println("Start price: " + vacsArr[n][8] + "$");
                        System.out.println("\n c - Continue booking, r - Return");

                        Scanner inpS = new Scanner(System.in);
                        lt = inpS.nextLine();

                        int trIndex, dayIndex;
                        byte iclFood;
                        if (Objects.equals(lt, "c")) {
                            String[] transports = vacs.getTransportArray(vacsArr[n][5]);
                            System.out.println("\nChoose the preferred transport type:");
                            for(int i = 0;i<transports.length;i++)
                                System.out.println((i+1) + ". " + transports[i]);
                            trIndex = inp.nextInt() - 1;
                            if(trIndex >= transports.length)
                                break;

                            String[] days = vacs.getDaysArray(vacsArr[n][6]);
                            System.out.println("\nChoose the preferred number of days:");
                            for(int i = 0;i<days.length;i++)
                                System.out.println((i+1) + ". " + days[i] + " day(s)");
                            dayIndex = inp.nextInt() - 1;
                            if(dayIndex >= days.length)
                                break;
                            //System.out.println("Foopooodooododfodfodod - " + vacsArr[n][7]);
                            if(Objects.equals(vacsArr[n][7], "1")){
                                System.out.println("Would you like to include food? yes/no");
                                iclFood = (byte) (Objects.equals(inpS.nextLine(),"yes") ? 1 : 0);
                            }
                            else{
                                iclFood = 0;
                            }

                            vacs.makeBooking(vacsArr[n][0],transports[trIndex],days[dayIndex],iclFood);
                            System.out.println("\n Thanks for using our product! You`ve created a booking!");
                            logs.writeLog("Booking created!");
                            Thread.sleep(1000);
                            break;
                        }
                    }
                    logs.writeLog("Returning to the Main Menu...");
                    break;

                case 2:
                    String[][] books = vacs.getBookings();
                    if(books.length == 0){
                        System.out.println("You have no bookings! To make one, choose the corresponding tab in Main Menu");
                        break;
                    }
                    System.out.println("Your bookings:");

                    for(int i = 0;i< books.length;i++){
                        System.out.println((i+1) + ". '" + books[i][1] + "', " + books[i][3] + " day(s), " + books[i][2] +
                                ", food " +(books[i][4] == "0" ? "not icluded" : "included"));
                    }
                    logs.writeLog("Showing made booking...");

                    System.out.println("\n1-" + books.length + " - delete the corresponding booking, 0 - Return to menu");
                    int answ = inp.nextInt();
                    if(answ == 0)
                        break;
                    answ = answ - 1;
                    if(answ >= 0 && answ < books.length){
                        vacs.deleteBooking(books[answ][0]);
                        System.out.println("Your booking was successfully deleted!");
                        logs.writeLog("The booking deleted!");
                        Thread.sleep(1000);
                    }
                    break;

                case 3:
                    Logs l = new Logs();
                    logs.writeLog("\nShowing logs...");
                    l.printLogs();
                    break;

                case 4:
                    logs.writeLog("Showing guide...");
                    System.out.println("To use this program, you need to have a keyboard.\nType a number to choose " +
                            "the item from a numeric list on the screen. Type the needed information if the programme" +
                            " asks for it.\nThat`s simple as that is!)");
                    Thread.sleep(5000);
                    break;

                case 5:
                    logs.writeLog("Exiting...");
                    break;

                default:
                    System.out.println("Please, try again");
                    break;
            }
        }
    }
}