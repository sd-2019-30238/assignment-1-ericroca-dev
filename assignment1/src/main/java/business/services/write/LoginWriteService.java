package business.services.write;

import business.interfaces.write.LoginWrite;
import data.implementation.UserDAOImpl;
import data.service.UserDAO;
import models.User;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginWriteService implements LoginWrite {

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
}
