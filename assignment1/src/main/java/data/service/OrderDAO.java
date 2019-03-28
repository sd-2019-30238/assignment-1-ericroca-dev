package data.service;

public interface OrderDAO {

    Integer addOrder(Integer userID, String details, String status);

    void listOrders();

    void updateOrder(Integer orderID, Integer userID, String details, String status);

    void deleteOrder(Integer orderID);
}
