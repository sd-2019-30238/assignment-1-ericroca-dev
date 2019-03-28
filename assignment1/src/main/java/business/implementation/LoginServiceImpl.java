package business.implementation;

import business.service.LoginService;
import data.implementation.UserDAOImpl;
import data.service.UserDAO;
import models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServiceImpl implements LoginService {

    @Override
    public String createAccount(String username, String password) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{3,15}$");
        Matcher usernameMatcher = pattern.matcher(username);
        Matcher passwordMatcher = pattern.matcher(password);
        if (usernameMatcher.matches() && passwordMatcher.matches()) {
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.findByUsername(username);
            if (user != null && user.getUsername().equals(username)) {
                return "This username already exists!";
            } else {
                userDAO.addUser(username, password, "user");
                return "OK";
            }
        }
        return "Invalid username or password!";
    }

    @Override
    public String validateUser(String username, String password) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{3,15}$");
        Matcher usernameMatcher = pattern.matcher(username);
        Matcher passwordMatcher = pattern.matcher(password);
        if (usernameMatcher.matches() && passwordMatcher.matches()) {
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.findByUsername(username);
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getRole();
            } else {
                return "Invalid username or password!";
            }
        }
        return "Invalid username or password!";
    }
}
