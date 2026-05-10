package Services;

import Entities.User;

public interface UserService {

    void createUser(String userID, String userName);
    void addCard(String userID, String cardID, double creditLimit);
    User getUser(String userID);
}
