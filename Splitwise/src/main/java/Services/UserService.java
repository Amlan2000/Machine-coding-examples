package Services;

import Entities.User;

public interface UserService {

    void addUser(String userID, String name, String emailID, String phone);

    User getUser(String userID);

    void findUsersWithPositiveDueBalance();

    void findUserExpenses(String userID);
}
