package business.interfaces.read;

import models.User;

public interface LoginRead {

    String validateUser(String username, String password);

    User getUserByUsername(String username);
}
