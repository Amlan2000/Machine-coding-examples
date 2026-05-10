package org.main;

import Service.BookingService;
import Service.FlightService;
import Service.Impl.BookingServiceImpl;
import Service.Impl.FlightServiceImpl;
import Service.Impl.UserServiceImpl;
import Service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        FlightService flightService = new FlightServiceImpl();
        UserService userService = new UserServiceImpl();

        BookingService bookingService =
                new BookingServiceImpl(flightService, userService);

        System.out.printf("Hello and welcome!");
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your name: ");
//        String name = sc.nextLine();
//        System.out.println("Hello, " + name + "!");


        userService.addUser("1","Vinit",5000.00);
        userService.addUser("2","Neha",1500.00);

        flightService.addFlight("1","123","AI","DL","BLR",2,10,11);
        flightService.addInventory("1", "F1", 3000.0, Arrays.asList("3a", "4c", "5b"));
        flightService.addInventory("1", "F2", 4500.0, Arrays.asList("1a", "1b"));

        flightService.addFlight("2","156","6E","DL","BLR",2,14,15);
        flightService.addInventory("2", "F1", 3000.0, Arrays.asList("6a", "8c", "5b"));
        flightService.addInventory("2", "F2", 4500.0, Arrays.asList("14a", "1b"));

        flightService.addFlight("3","234","6E","DL","HYD",2,15,16);
        flightService.addFlight("4","456","6E","AMD","CCU",2,18,22);
        flightService.addFlight("5","456","UK","DL","BLR",2,19,21);


        flightService.searchFlight("DL","BLR",2,1);

        flightService.searchFlight("DL","BLR",2,1,"AI","PRICE","DESC");

        bookingService.bookFlight("1","1","DL","BLR","123","6E",2,"F1", List.of("4c"));

    }
}