package mediator.events;

import data.implementation.OrderDAOImpl;
import data.service.OrderDAO;
import models.Order;

public class FindOrder {

    public Order findOrder(Integer id) {
        OrderDAO orderDAO = new OrderDAOImpl();
        Order order = orderDAO.findById(id);
        return order;
    }
}
