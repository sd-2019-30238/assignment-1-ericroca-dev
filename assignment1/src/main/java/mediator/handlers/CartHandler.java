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
    public <T> T handle(Query query) {
        if (query == Query.GETUSERCART) {
            User user = new FindUser().findUser(username);
            List<Cart> cartList = new FindUserCart().findUserCart(user);
            List<Deal> dealList = new FindUserDeals().findUserDeals(cartList);
            return (T) dealList;
        } else if (query == Query.ADDTOCART) {
            User user = new FindUser().findUser(username);
            Deal deal = new FindDeal().findDeal(name);
            new AddToCart().addToCart(user, deal);
        } else if (query == Query.DELETEITEM) {
            User user = new FindUser().findUser(username);
            Deal deal = new FindDeal().findDeal(name);
            Cart cart = new FindCart().findCart(user, deal);
            new DeleteCart().deleteCart(cart);
        }
        return null;
    }
}
