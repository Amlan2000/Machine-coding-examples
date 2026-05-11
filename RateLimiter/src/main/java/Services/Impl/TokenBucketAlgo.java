package Services.Impl;

import Entities.User;
import Exceptions.RateLimittingException;
import Services.RateLimittingAlgo;

import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.min;

public class TokenBucketAlgo implements RateLimittingAlgo {

    public static class Bucket{
        double currentTokens;
        long lastRefill;

        Bucket(double maxCapacity){
            this.currentTokens=maxCapacity;
            this.lastRefill=System.currentTimeMillis();
        }
    }

    private final ConcurrentHashMap<String,Bucket> userBucketsMap = new ConcurrentHashMap<>();

    @Override
    public void rateLimitCheck(User user){

        String userID = user.getUserID();
        int userMaxRequests = user.getTier().getMaxRequests(); //eg. 1000req/hour/user

        double refillRateMs=(double) userMaxRequests/(60*1000); //eg. 1 tokens/sec

        userBucketsMap.compute(userID,(id, bucket)->{

           if(bucket==null){
              bucket= new Bucket(userMaxRequests);
           }

           long now = System.currentTimeMillis();
           long timeElapsed = now - bucket.lastRefill;
           double tokensToAdd = timeElapsed*refillRateMs;

           bucket.currentTokens = min(userMaxRequests, bucket.currentTokens+tokensToAdd);
           bucket.lastRefill=now;

           if(bucket.currentTokens<1.0){
               throw new RateLimittingException("429: Too many requests.\n");
           }

           bucket.currentTokens-=1.0;
           System.out.println("Token bucket log: User " + userID + " | Tokens remaining: " + (int)bucket.currentTokens);

            return bucket;



        });


    }

    @Override
    public void resetUserBucket(String userID){
        userBucketsMap.remove(userID);
    }

    @Override
    public int getRemainingQuota(String userID){
        Bucket b = userBucketsMap.get(userID);
        return (b == null) ? 0 : (int) b.currentTokens;
    }
}
