package mediator.events;

import data.implementation.CartDAOImpl;
import data.service.CartDAO;
import models.Cart;

public class DeleteCart {

    public void deleteCart(Cart cart) {
        CartDAO cartDAO = new CartDAOImpl();
        cartDAO.deleteCart(cart.getId());
    }
}
