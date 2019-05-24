package mediator;

import models.Deal;
import models.Discount;
import models.Order;
import models.User;

import java.util.List;

public interface Mediator {

    List<Deal> getUserCart(String username);

    void addToCart(String username, String name);

    void deleteItem(String username, String name);
}
