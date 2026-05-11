package Services.Impl;

import Entities.User;
import Exceptions.RateLimittingException;
import Services.RateLimittingAlgo;
import Services.UserService;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowAlgo implements RateLimittingAlgo {

    private static final long WINDOW_SIZE_MS = 2000; // 1 minute

    private static class UserStats{
        AtomicInteger count;
        long windowStartTime;

        UserStats(long startTime){
            this.count= new AtomicInteger(0);
            this.windowStartTime=startTime;
        }
    }

    private final ConcurrentHashMap<String,UserStats> fixedWindowBuckets = new ConcurrentHashMap<>();

    @Override
     public void rateLimitCheck(User user){

        String userID = user.getUserID();
        int maxAllowed = user.getTier().getMaxRequests();
        long now = System.currentTimeMillis();

        fixedWindowBuckets.compute(userID,(id,stats)->{
            if(stats==null || (now-stats.windowStartTime)>= WINDOW_SIZE_MS){
                stats = new UserStats(now);
            }

            if(stats.count.get()>= maxAllowed){
                throw new RateLimittingException("429: Too many requests. Try again later.");
            }

            stats.count.incrementAndGet();
            System.out.println("Fixed Window log: User " + id + " | Request: " + stats.count.get() + "/" + maxAllowed);
            return stats;
        });
    }


    @Override
    public void resetUserBucket(String userID){
        fixedWindowBuckets.remove(userID);
    }

    @Override
    public int getRemainingQuota(String userID){

        UserStats stats = fixedWindowBuckets.get(userID);
        return (stats==null)?0:stats.count.get();
    }


}
