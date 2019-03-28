package data.service;

import models.Cart;

import java.util.List;

public interface CartDAO {

    Integer addCart(Integer userID, Integer dealID);

    void listCarts();

    void updateCart(Integer cartID, Integer userID, Integer dealID);

    void deleteCart(Integer cartID);

    List<Cart> getUserCart(Integer userID);

    Cart findByIDs(Integer userID, Integer dealID);
}
