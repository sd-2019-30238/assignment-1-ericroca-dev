package mediator.events;

import data.implementation.OrderDAOImpl;
import data.service.OrderDAO;
import models.Order;

import java.util.List;

public class GetOrders {

    public List<Order> getOrders() {
        OrderDAO orderDAO = new OrderDAOImpl();
        return orderDAO.getOrders();
    }
}
