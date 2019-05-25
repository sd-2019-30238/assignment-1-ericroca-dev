package business.services.read;

import business.interfaces.read.LoginRead;
import data.implementation.UserDAOImpl;
import data.service.UserDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.User;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginReadService implements LoginRead {

    private Mediator mediator;

    public LoginReadService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public String validateUser(String username, String password) {
        return mediator.validateUser(username, password);
    }

    @Override
    public User getUserByUsername(String username) {
        return mediator.getUserByUsername(username);
    }
}
