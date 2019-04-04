package business.implementation;

import business.service.OrderService;
import data.implementation.CartDAOImpl;
import data.implementation.DealDAOImpl;
import data.implementation.OrderDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.CartDAO;
import data.service.DealDAO;
import data.service.OrderDAO;
import data.service.UserDAO;
import models.Deal;
import models.Order;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getOrders() {
        OrderDAO orderDAO = new OrderDAOImpl();
        return orderDAO.getOrders();
    }

    @Override
    public void checkout(String username, List<String> names, List<String> prices) {
        UserDAO userDAO = new UserDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();

        List<Deal> dealList = new ArrayList<>();
        for (int i = 0; i < names.size(); ++i) {
            dealList.add(dealDAO.findByName(names.get(i)));

            if (dealList.get(i).getQuantity() == 0) {
                return;
            }
        }

        for (Deal deal : dealList) {
            dealDAO.updateDeal(deal.getId(), deal.getPrice(), deal.getName(), deal.getType(),
                    deal.getQuantity() - 1);
        }

        User user = userDAO.findByUsername(username);

        String details = "";
        for (int i = 0; i < names.size(); ++i) {
            details += "Name: " + names.get(i) + ", Price: " + prices.get(i) + "; ";
        }

        if (user != null) {
            orderDAO.addOrder(user.getId(), details, "unchecked");
        }

        cartDAO.deleteByID(user.getId());
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

    @Override
    public void updateStatus(Integer ID, String status) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
        Matcher statusMatcher = pattern.matcher(status);

        if (statusMatcher.matches()) {
            OrderDAO orderDAO = new OrderDAOImpl();

            Order order = orderDAO.findById(ID);
            if (order != null) {
                orderDAO.updateOrder(order.getId(), order.getUserID(), order.getDetails(), status);
            }
        }
    }
}
