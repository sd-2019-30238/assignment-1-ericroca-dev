package business.service;

import models.Deal;

import java.util.List;

public interface CartService {

    void addToCart(String username, String name);

    List<Deal> getUserCart(String username);

    void deleteItem(String username, String name);
}
