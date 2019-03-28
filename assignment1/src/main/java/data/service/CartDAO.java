package data.service;

public interface CartDAO {

    Integer addCart(Integer userID, Integer dealID);

    void listCarts();

    void updateCart(Integer cartID, Integer userID, Integer dealID);

    void deleteCart(Integer cartID);
}
