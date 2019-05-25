package mediator.events;

import data.implementation.UserDAOImpl;
import data.service.UserDAO;

public class AddUser {

    public void addUser(String username, String password) {
        UserDAO userDAO = new UserDAOImpl();
        userDAO.addUser(username, password, "user");
    }
}
