package mediator.events;

import data.implementation.OrderDAOImpl;
import data.service.OrderDAO;
import models.Order;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class GetUserDeliveredOrders {

    public List<Order> getUserDeliveredOrders(User user) {
        OrderDAO orderDAO = new OrderDAOImpl();

        List<Order> orderList = new ArrayList<>();
        if (user != null) {
            orderList = orderDAO.getUserDeliveredOrders(user.getId());
        }

        return orderList;
    }
}
