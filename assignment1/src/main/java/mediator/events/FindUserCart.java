package mediator.events;

import data.implementation.CartDAOImpl;
import data.service.CartDAO;
import models.Cart;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class FindUserCart {

    public List<Cart> findUserCart(User user) {
        CartDAO cartDAO = new CartDAOImpl();
        List<Cart> cartList = new ArrayList<>();
        if (user != null) {
            cartList = cartDAO.getUserCart(user.getId());
        }
        return cartList;
    }
}
