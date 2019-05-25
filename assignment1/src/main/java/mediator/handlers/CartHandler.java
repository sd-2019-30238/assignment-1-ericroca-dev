package mediator.handlers;

import mediator.events.*;
import models.Cart;
import models.Deal;
import models.User;

import java.util.List;

public class CartHandler implements Handler{

    private String username;
    private String name;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDeal(String name) {
        this.name = name;
    }

    @Override
    public <T> T handle(Request request) {
        if (request == Request.GETUSERCART) {
            User user = new FindUser().findUser(username);
            List<Cart> cartList = new FindUserCart().findUserCart(user);
            List<Deal> dealList = new FindUserDeals().findUserDeals(cartList);
            return (T) dealList;
        } else if (request == Request.ADDTOCART) {
            User user = new FindUser().findUser(username);
            Deal deal = new FindDealByName().findDealByName(name);
            new AddToCart().addToCart(user, deal);
        } else if (request == Request.DELETEITEM) {
            User user = new FindUser().findUser(username);
            Deal deal = new FindDealByName().findDealByName(name);
            Cart cart = new FindCart().findCart(user, deal);
            new DeleteCart().deleteCart(cart);
        }
        return null;
    }
}
