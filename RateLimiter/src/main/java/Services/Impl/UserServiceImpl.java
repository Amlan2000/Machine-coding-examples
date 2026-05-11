package Services.Impl;

import Entities.Tier;
import Entities.User;
import Enums.TierType;
import Exceptions.RateLimittingException;
import Services.UserService;

import java.util.HashMap;

public class UserServiceImpl implements UserService {

    public HashMap<String, User> userHashMap = new HashMap<>();

    @Override
    public void addUser(String userID, String tierType){

        if(!userHashMap.isEmpty() && userHashMap.containsKey(userID)){
            throw new RateLimittingException("User: " + userID + " is already added. \n");
        }

        Tier tier = generateTierObject(tierType);
        User user = User.builder().userID(userID).tier(tier).build();

        userHashMap.put(userID,user);

        System.out.println("User: "+userID+" is successfully created. \n");

    }

    private Tier generateTierObject(String tierType){

        tierType=tierType.toUpperCase();

        Tier tier;

        if(TierType.valueOf(tierType).equals(TierType.PRO)){
            tier = Tier.builder().tierID("2").tierType(TierType.PRO).maxRequests(20).build();
        }
        else{
            tier = Tier.builder().tierID("1").tierType(TierType.BASIC).maxRequests(10).build();
        }

        return tier;
    }

    @Override
    public User getUser(String userID){
        return userHashMap.get(userID);
    }

}
