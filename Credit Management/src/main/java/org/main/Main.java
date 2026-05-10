package org.main;

import Entities.User;
import Services.Impl.TransactionServiceImpl;
import Services.Impl.UserServiceImpl;
import Services.TransactionService;

public class Main {

    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl(userService);

        System.out.println("Hello Amlan!");


        userService.createUser("u1", "Alice");
        userService.addCard("u1","1",10000);

        transactionService.addTransaction("u1",8000,"TRAVEL","PENDING");
    }
}