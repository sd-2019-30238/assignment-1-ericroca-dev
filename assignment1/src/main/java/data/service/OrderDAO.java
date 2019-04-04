package data.service;

import models.Order;

import java.util.List;

public interface OrderDAO {

    Integer addOrder(Integer userID, String details, String status);

    void listOrders();

    void updateOrder(Integer orderID, Integer userID, String details, String status);

    void deleteOrder(Integer orderID);

    List<Order> getOrders();

    List<Order> getUserOrders(Integer userID);

    Order findById(Integer ID);
}
