package Service.Impl;

import Entities.User;
import Service.UserService;

import java.util.HashMap;

public class UserServiceImpl implements UserService {

     public HashMap<String, User> userList = new HashMap<>();

    @Override
    public void addUser(String userID, String name, Double funds){

        if(!userList.isEmpty() && userList.containsKey(userID)){
            System.out.println("\n User is already added. \n");
            return;
        }

        User user = new User(userID,name,funds);

        userList.put(userID,user);
        System.out.println("\n User is successfully added.\n");

    }

    @Override
    public User getUser(String userID){
        return userList.get(userID);
    }
}
