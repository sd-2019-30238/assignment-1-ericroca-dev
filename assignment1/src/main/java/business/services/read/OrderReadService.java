package business.services.read;

import business.interfaces.read.OrderRead;
import data.implementation.OrderDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.OrderDAO;
import data.service.UserDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.Order;
import models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderReadService implements OrderRead {

    private Mediator mediator;

    public OrderReadService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public List<Order> getOrders() {
        return mediator.getOrders();
    }

    @Override
    public List<Order> getUserOrders(String username) {
        return mediator.getUserOrders(username);
    }

    @Override
    public List<Order> getUserDeliveredOrders(String username) {
        return mediator.getUserDeliveredOrders(username);
    }
}
