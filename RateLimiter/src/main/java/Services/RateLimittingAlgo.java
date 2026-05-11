package Services;

import Entities.User;

public interface RateLimittingAlgo {

    void rateLimitCheck(User user);
    void resetUserBucket(String userID);
    int getRemainingQuota(String userID);
}
