   package org.main;


import Entities.User;
import Services.Impl.UserServiceImpl;
import Services.RateLimittingAlgo;
import Services.UserService;
import Factory.RateLimitingFactory;
import Utils.TestingAPIUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, there. Have a great day ahead! \n");

        UserService userService = new UserServiceImpl();

        userService.addUser("1", "basic");
        userService.addUser("2", "pro");
        userService.addUser("3", "basic");

        // Testing Fixed window algo
        RateLimittingAlgo rateLimittingAlgo = RateLimitingFactory.setRateLimitingAlgo("fixedWindow");

        rateLimittingAlgo.resetUserBucket("1");

        TestingAPIUtil testingAPIUtil = new TestingAPIUtil();

        User user = userService.getUser("1");


//        System.out.println("--- Starting Rate Limit Test ---");
//        for (int i = 1; i <= 5; i++) {
//            try {
//                System.out.print("Request #" + i + ": ");
//                testingAPIUtil.testingAPI(user,rateLimittingAlgo);
//                System.out.println("SUCCESS");
//            } catch (RuntimeException e) {
//                System.out.println("BLOCKED - " + e.getMessage());
//            }
//        }

        // Simulate 15 concurrent threads hitting a limit of 10
        ExecutorService executorService = Executors.newFixedThreadPool(15);

        for (int i = 0; i < 15; i++) {
            executorService.execute(() -> {
                try {
                    testingAPIUtil.testingAPI(user, rateLimittingAlgo);
                } catch (Exception e) {
                    System.out.println("BLOCKED");
                }
            });

            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);

//            Thread.sleep(2000); //

            try {
                testingAPIUtil.testingAPI(user, rateLimittingAlgo);
                System.out.println("SUCCESS: Window reset correctly!");
            } catch (Exception e) {
                System.err.println("FAIL: Window did not reset.");
            }


        }




        // Testing token bucket algo


        RateLimittingAlgo rateLimittingAlgo2 = RateLimitingFactory.setRateLimitingAlgo("tokenBucket");

        rateLimittingAlgo2.resetUserBucket("2");


        User user2 = userService.getUser("2");


        for (int i = 0; i < 25; i++) {
            executorService.execute(() -> {
                try {
                    testingAPIUtil.testingAPI(user2, rateLimittingAlgo2);
                } catch (Exception e) {
                    System.out.println("BLOCKED");
                }
            });
        }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);

//            Thread.sleep(7000);

            try {
                testingAPIUtil.testingAPI(user2, rateLimittingAlgo2);
                System.out.println("SUCCESS: Token added to bucket correctly!\n");
            } catch (Exception e) {
                System.err.println("FAIL: Token didnt add \n.");
            }



    }
}
