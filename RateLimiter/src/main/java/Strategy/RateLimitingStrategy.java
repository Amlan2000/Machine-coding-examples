package Strategy;

import Services.Impl.FixedWindowAlgo;
import Services.Impl.TokenBucketAlgo;
import Services.RateLimittingAlgo;

public class RateLimitingStrategy {
    
    public static RateLimittingAlgo setRateLimitingAlgo(String strategy){
        RateLimittingAlgo rateLimittingAlgo = null;

        if(strategy.equals("fixedWindow")){
            rateLimittingAlgo = new FixedWindowAlgo();
        }
        else if(strategy.equalsIgnoreCase("tokenBucket")){
            rateLimittingAlgo = new TokenBucketAlgo();

        }
        return rateLimittingAlgo;
    }
}
