package business.implementation;

import business.service.CartService;
import data.implementation.CartDAOImpl;
import data.implementation.DealDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.CartDAO;
import data.service.DealDAO;
import data.service.UserDAO;
import models.Deal;
import models.User;

public class CartServiceImpl implements CartService {

    @Override
    public void addToCart(String username, String name) {
        UserDAO userDAO = new UserDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();

        User user  = userDAO.findByUsername(username);
        Deal deal = dealDAO.findByName(name);

        if (user != null && deal != null) {
            cartDAO.addCart(user.getId(), deal.getId());
        }
    }
}
