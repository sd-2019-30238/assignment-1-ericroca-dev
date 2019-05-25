package mediator.handlers;

import mediator.events.*;
import models.Deal;
import models.Order;
import models.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderHandler implements Handler {

    private String username;
    private List<String> names;
    private String status;
    private Integer id;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public <T> T handle(Request request) {
        if (request == Request.GETORDERS) {
            List<Order> orderList = new GetOrders().getOrders();
            return (T) orderList;
        } else if (request == Request.GETUSERORDERS) {
            User user  = new FindUser().findUser(username);
            List<Order> orderList = new GetUserOrders().getUserOrders(user);
            return (T) orderList;
        } else if (request == Request.GETUSERDELIVEREDORDERS) {
            User user  = new FindUser().findUser(username);
            List<Order> orderList = new GetUserDeliveredOrders().getUserDeliveredOrders(user);
            return (T) orderList;
        } else if (request == Request.CHECKOUT) {
            List<Deal> dealList = new GetCartDeals().getCartDeals(names);
            for (int i = 0; i < dealList.size(); ++i) {
                new EditDeal().editDeal(dealList.get(i).getId(), dealList.get(i).getPrice(), dealList.get(i).getName(),
                        dealList.get(i).getType(), dealList.get(i).getQuantity() - 1);
                new ApplyDecoratorDiscount().applyDecoratorDiscount(dealList.get(i));
            }
            String details = new CreateDetails().createDetails(names, dealList);
            User user = new FindUser().findUser(username);
            new AddOrder().addOrder(user, details);
            new DeleteUserCart().deleteUserCart(user);
        } else if (request == Request.UPDATESTATUS) {
            Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
            Matcher statusMatcher = pattern.matcher(status);

            if (statusMatcher.matches()) {
                Order order = new FindOrder().findOrder(id);
                if (order != null) {
                    new EditOrder().editOrder(order, status);
                }
            }
        }
        return null;
    }
}
