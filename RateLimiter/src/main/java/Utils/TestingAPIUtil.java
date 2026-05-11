package Utils;

import Entities.User;
import Services.RateLimittingAlgo;

public class TestingAPIUtil {

    public void testingAPI(User user, RateLimittingAlgo rateLimittingAlgo){

        System.out.println("Called API.\n");

        rateLimittingAlgo.rateLimitCheck(user);

    }

}
