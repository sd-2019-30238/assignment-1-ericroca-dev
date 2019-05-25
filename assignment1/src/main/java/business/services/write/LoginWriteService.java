package business.services.write;

import business.interfaces.write.LoginWrite;
import data.implementation.UserDAOImpl;
import data.service.UserDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.User;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginWriteService implements LoginWrite {

    private Mediator mediator;

    public LoginWriteService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public String createAccount(String username, String password) {
        return mediator.createAccount(username, password);
    }
}
