package mediator.events;

import data.implementation.CartDAOImpl;
import data.service.CartDAO;
import models.Deal;
import models.User;

public class AddToCart {

    public void addToCart(User user, Deal deal) {
        CartDAO cartDAO = new CartDAOImpl();
        cartDAO.addCart(user.getId(), deal.getId());
    }
}
