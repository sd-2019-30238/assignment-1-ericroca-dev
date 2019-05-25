package mediator.handlers;

import mediator.events.AddUser;
import mediator.events.FindUser;
import models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginHandler implements Handler {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public <T> T handle(Request request) {
        if (request == Request.VALIDATEUSER) {
            Pattern pattern = Pattern.compile("^[a-z0-9_-]{3,15}$");
            Matcher usernameMatcher = pattern.matcher(username);
            Matcher passwordMatcher = pattern.matcher(password);
            if (usernameMatcher.matches() && passwordMatcher.matches()) {
                User user = new FindUser().findUser(username);
                if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return (T) user.getRole();
                } else {
                    return (T) "Invalid username or password!";
                }
            }
            return (T) "Invalid username or password!";
        } else if (request == Request.GETUSER) {
            Pattern pattern = Pattern.compile("^[a-z0-9_-]{3,15}$");
            Matcher usernameMatcher = pattern.matcher(username);
            if (usernameMatcher.matches()) {
                return (T) new FindUser().findUser(username);
            }
            return null;
        } else if (request == Request.CREATEACCOUNT) {
            Pattern pattern = Pattern.compile("^[a-z0-9_-]{3,15}$");
            Matcher usernameMatcher = pattern.matcher(username);
            Matcher passwordMatcher = pattern.matcher(password);
            if (usernameMatcher.matches() && passwordMatcher.matches()) {
                User user = new FindUser().findUser(username);
                if (user != null && user.getUsername().equals(username)) {
                    return (T) "This username already exists!";
                } else {
                    new AddUser().addUser(username, password);
                    return (T) "OK";
                }
            }
            return (T) "Invalid username or password!";
        }
        return null;
    }
}
