package mediator.events;

import data.implementation.UserDAOImpl;
import data.service.UserDAO;
import models.User;

public class FindUser {

    public User findUser(String username) {
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.findByUsername(username);
        return user;
    }
}
