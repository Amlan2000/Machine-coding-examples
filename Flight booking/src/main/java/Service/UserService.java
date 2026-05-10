package Service;


import Entities.User;

public interface UserService {

    void addUser(String userID, String name, Double fund);

    User getUser(String userID);
}
