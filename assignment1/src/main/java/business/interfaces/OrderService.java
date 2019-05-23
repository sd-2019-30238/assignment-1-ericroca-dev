package business.interfaces;

import models.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    void checkout(String username, List<String> names, List<String> prices);

    List<Order> getUserOrders(String username);

    List<Order> getUserDeliveredOrders(String username);

    void updateStatus(Integer ID, String status);
}
