package mediator.events;

import data.implementation.CartDAOImpl;
import data.service.CartDAO;
import models.User;

public class DeleteUserCart {

    public void deleteUserCart(User user) {
        CartDAO cartDAO = new CartDAOImpl();
        cartDAO.deleteByID(user.getId());
    }
}
