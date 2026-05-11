package Services;

import Entities.User;

public interface UserService {

    void addUser(String userID, String tierType);
    User getUser(String userID);

    }
