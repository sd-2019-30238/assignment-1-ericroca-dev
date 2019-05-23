package business.services.read;

import business.interfaces.read.CartRead;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class CartReadService implements CartRead {

    @Override
    public List<Deal> getUserCart(String username) {
        UserDAO userDAO = new UserDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();

        User user = userDAO.findByUsername(username);
        List<Cart> cartList = new ArrayList<>();
        if (user != null) {
            cartList = cartDAO.getUserCart(user.getId());
        }

        List<Deal> dealList = new ArrayList<>();
        for (Cart cart : cartList) {
            dealList.add(dealDAO.findByID(cart.getDealID()));
        }

        return dealList;
    }
}
