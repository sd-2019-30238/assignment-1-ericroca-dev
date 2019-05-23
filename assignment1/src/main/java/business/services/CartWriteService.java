package business.services;

import business.interfaces.CartWrite;
import data.implementation.CartDAOImpl;
import data.implementation.DealDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.CartDAO;
import data.service.DealDAO;
import data.service.UserDAO;
import models.Cart;
import models.Deal;
import models.User;
import org.springframework.stereotype.Service;

@Service
public class CartWriteService implements CartWrite {

    @Override
    public void addToCart(String username, String name) {
        UserDAO userDAO = new UserDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();
        System.out.println(name);
        User user  = userDAO.findByUsername(username);
        Deal deal = dealDAO.findByName(name);

        if (user != null && deal != null) {
            cartDAO.addCart(user.getId(), deal.getId());
        }
    }

    @Override
    public void deleteItem(String username, String name) {
        UserDAO userDAO = new UserDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();

        User user = userDAO.findByUsername(username);
        Deal deal = dealDAO.findByName(name);

        Cart cart = cartDAO.findByIDs(user.getId(), deal.getId());
        cartDAO.deleteCart(cart.getId());
    }
}
