package mediator.events;

import data.implementation.OrderDAOImpl;
import data.service.OrderDAO;
import models.Order;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class GetUserOrders {

    public List<Order> getUserOrders(User user) {
        OrderDAO orderDAO = new OrderDAOImpl();

        List<Order> orderList = new ArrayList<>();
        if (user != null) {
            orderList = orderDAO.getUserOrders(user.getId());
        }

        return orderList;
    }
}
