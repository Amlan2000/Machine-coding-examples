package org.main;

import Entities.User;
import Enums.ExpenseType;
import Services.ExpenseService;
import Services.Impl.ExpenseServiceImpl;
import Services.Impl.UserServiceImpl;
import Services.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        ExpenseService expenseService = new ExpenseServiceImpl(userService);

        System.out.println("Hello, there. Have a great day ahead! \n");

        userService.addUser("1","goofie","goofie@goofie.com","444");
        userService.addUser("2","tom","tom@goofie.com","555");
        userService.addUser("3","jerry","jerry@goofie.com","6666");
        userService.addUser("4","noddy","noddy@goofie.com","777");

        expenseService.addExpense("1","Food", BigDecimal.valueOf(200),"1","1",3, ExpenseType.EQUAL,Arrays.asList("1","2","3"), List.of());
        expenseService.addExpense("2","Cable",BigDecimal.valueOf(200),"1","1",3, ExpenseType.EXACT,Arrays.asList("1","2","3"), Arrays.asList(BigDecimal.valueOf(40),BigDecimal.valueOf(50),BigDecimal.valueOf(10)));
        expenseService.addExpense("3","Hotels",BigDecimal.valueOf(200),"4","4",3, ExpenseType.PERCENT,Arrays.asList("2","3","4"), Arrays.asList(BigDecimal.valueOf(30),BigDecimal.valueOf(50),BigDecimal.valueOf(20)));

        userService.findUsersWithPositiveDueBalance();

        User user = userService.getUser("1");
        System.out.println("User Name: "+user.getUserName());


    }
}