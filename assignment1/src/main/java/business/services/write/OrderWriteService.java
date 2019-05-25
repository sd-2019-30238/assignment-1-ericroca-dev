package business.services.write;

import business.interfaces.write.OrderWrite;
import data.implementation.CartDAOImpl;
import data.implementation.DealDAOImpl;
import data.implementation.OrderDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.CartDAO;
import data.service.DealDAO;
import data.service.OrderDAO;
import data.service.UserDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderWriteService implements OrderWrite {

    private Mediator mediator;

    public OrderWriteService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public void checkout(String username, List<String> names) {
        mediator.checkout(username, names);
    }

    @Override
    public void updateStatus(Integer id, String status) {
        mediator.updateStatus(id, status);
    }
}
