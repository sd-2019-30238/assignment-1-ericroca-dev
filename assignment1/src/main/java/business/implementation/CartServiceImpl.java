package business.implementation;

import business.service.CartService;
import data.implementation.CartDAOImpl;
import data.implementation.DealDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.CartDAO;
import data.service.DealDAO;
import data.service.UserDAO;
import models.Cart;
import models.Deal;
import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartServiceImpl implements CartService {

    @Override
    @RequestMapping(value = "/{username}/cart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@PathVariable("username") String username, @RequestParam("name") String name) {
        UserDAO userDAO = new UserDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();

        User user  = userDAO.findByUsername(username);
        Deal deal = dealDAO.findByName(name);

        if (user != null && deal != null) {
            cartDAO.addCart(user.getId(), deal.getId());
        }
    }

    @Override
    @RequestMapping(value = "{username}/cart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Deal> getUserCart(@PathVariable("username") String username) {
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

    @Override
    @RequestMapping(value = "{username}/cart", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItem(@PathVariable("username") String username, @RequestParam("name") String name) {
        UserDAO userDAO = new UserDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();

        User user = userDAO.findByUsername(username);
        Deal deal = dealDAO.findByName(name);

        Cart cart = cartDAO.findByIDs(user.getId(), deal.getId());
        cartDAO.deleteCart(cart.getId());
    }
}
