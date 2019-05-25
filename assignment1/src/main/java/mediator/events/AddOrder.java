package mediator.events;

import data.implementation.OrderDAOImpl;
import data.service.OrderDAO;
import models.User;

public class AddOrder {

    public void addOrder(User user, String details) {
        OrderDAO orderDAO = new OrderDAOImpl();
        if (user != null) {
            orderDAO.addOrder(user.getId(), details, "unchecked");
        }
    }
}
