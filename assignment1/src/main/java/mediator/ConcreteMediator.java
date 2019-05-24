package mediator;

import mediator.handlers.CartHandler;
import mediator.handlers.Handler;
import mediator.handlers.Query;
import models.Deal;
import models.Discount;
import models.Order;
import models.User;

import java.util.List;

public class ConcreteMediator implements Mediator {

    @Override
    public List<Deal> getUserCart(String username) {
        Handler handler = new CartHandler();
        ((CartHandler) handler).setUsername(username);
        return handler.handle(Query.GETUSERCART);
    }

    @Override
    public void addToCart(String username, String name) {
        Handler handler = new CartHandler();
        ((CartHandler) handler).setUsername(username);
        ((CartHandler) handler).setDeal(name);
        handler.handle(Query.ADDTOCART);
    }

    @Override
    public void deleteItem(String username, String name) {
        Handler handler = new CartHandler();
        ((CartHandler) handler).setUsername(username);
        ((CartHandler) handler).setDeal(name);
        handler.handle(Query.DELETEITEM);
    }
}
