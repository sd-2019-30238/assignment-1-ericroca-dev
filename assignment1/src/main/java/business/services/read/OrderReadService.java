package business.services.read;

import business.interfaces.read.OrderRead;
import data.implementation.OrderDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.OrderDAO;
import data.service.UserDAO;
import models.Order;
import models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderReadService implements OrderRead {

    @Override
    public List<Order> getOrders() {
        OrderDAO orderDAO = new OrderDAOImpl();
        return orderDAO.getOrders();
    }

    @Override
    public List<Order> getUserOrders(String username) {
        UserDAO userDAO = new UserDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();

        User user = userDAO.findByUsername(username);
        List<Order> orderList = new ArrayList<>();
        if (user != null) {
            orderList = orderDAO.getUserOrders(user.getId());
        }

        return orderList;
    }

    @Override
    public List<Order> getUserDeliveredOrders(String username) {
        UserDAO userDAO = new UserDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();

        User user = userDAO.findByUsername(username);
        List<Order> orderList = new ArrayList<>();
        if (user != null) {
            orderList = orderDAO.getUserDeliveredOrders(user.getId());
        }

        return orderList;
    }
}
