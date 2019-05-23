package business.interfaces.read;

import models.Order;

import java.util.List;

public interface OrderRead {

    List<Order> getOrders();

    List<Order> getUserOrders(String username);

    List<Order> getUserDeliveredOrders(String username);
}
