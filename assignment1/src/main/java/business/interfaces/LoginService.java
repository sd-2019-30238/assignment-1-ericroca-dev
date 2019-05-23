package business.interfaces;

import models.User;

public interface LoginService {

    String createAccount(String username, String password);

    String validateUser(String username, String password);

    User getUserByUsername(String username);
}
