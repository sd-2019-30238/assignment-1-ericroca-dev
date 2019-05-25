package mediator.events;

import data.implementation.OrderDAOImpl;
import data.service.OrderDAO;
import models.Order;

public class EditOrder {

    public void editOrder(Order order, String status) {
        OrderDAO orderDAO = new OrderDAOImpl();
        if (order != null) {
            orderDAO.updateOrder(order.getId(), order.getUserID(), order.getDetails(), status);
        }
    }
}
