package mediator.events;

import data.implementation.CartDAOImpl;
import data.service.CartDAO;
import models.Cart;
import models.Deal;
import models.User;

public class FindCart {

    public Cart findCart(User user, Deal deal) {
        CartDAO cartDAO = new CartDAOImpl();
        Cart cart = cartDAO.findByIDs(user.getId(), deal.getId());
        return cart;
    }
}
