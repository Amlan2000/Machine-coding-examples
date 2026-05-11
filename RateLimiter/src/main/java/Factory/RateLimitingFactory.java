package Factory;

import Services.Impl.FixedWindowAlgo;
import Services.Impl.TokenBucketAlgo;
import Services.RateLimittingAlgo;

public class RateLimitingFactory {
    
    public static RateLimittingAlgo setRateLimitingAlgo(String factory){
        RateLimittingAlgo rateLimittingAlgo = null;

        if(factory.equals("fixedWindow")){
            rateLimittingAlgo = new FixedWindowAlgo();
        }
        else if(factory.equalsIgnoreCase("tokenBucket")){
            rateLimittingAlgo = new TokenBucketAlgo();

        }
        return rateLimittingAlgo;
    }
}
