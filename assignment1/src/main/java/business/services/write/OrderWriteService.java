package business.services.write;

import business.interfaces.write.OrderWrite;
import data.implementation.CartDAOImpl;
import data.implementation.DealDAOImpl;
import data.implementation.OrderDAOImpl;
import data.implementation.UserDAOImpl;
import data.service.CartDAO;
import data.service.DealDAO;
import data.service.OrderDAO;
import data.service.UserDAO;
import models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderWriteService implements OrderWrite {

    @Override
    public void checkout(String username, List<String> names, List<String> prices) {
        UserDAO userDAO = new UserDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();
        DealDAO dealDAO = new DealDAOImpl();

        List<Deal> dealList = new ArrayList<>();
        for (int i = 0; i < names.size(); ++i) {
            dealList.add(dealDAO.findByName(names.get(i)));

            if (dealList.get(i).getQuantity() == 0) {
                return;
            }
        }

        for (int i = 0; i < dealList.size(); ++i) {
            dealDAO.updateDeal(dealList.get(i).getId(), dealList.get(i).getPrice(), dealList.get(i).getName(),
                    dealList.get(i).getType(), dealList.get(i).getQuantity() - 1);

            ConcreteDiscountDecorator concreteDiscountDecorator = new ConcreteDiscountDecorator(new HalfOffDiscount(),
                    DiscountType.HALFOFF);
            concreteDiscountDecorator.applyDiscount(dealList.get(i));
        }

        User user = userDAO.findByUsername(username);

        String details = "";
        for (int i = 0; i < names.size(); ++i) {
            details += "Name: " + names.get(i) + ", Price: " + dealList.get(i).getPrice() + "; ";
        }

        if (user != null) {
            orderDAO.addOrder(user.getId(), details, "unchecked");
        }

        cartDAO.deleteByID(user.getId());
    }

    @Override
    public void updateStatus(Integer ID, String status) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
        Matcher statusMatcher = pattern.matcher(status);

        if (statusMatcher.matches()) {
            OrderDAO orderDAO = new OrderDAOImpl();

            Order order = orderDAO.findById(ID);
            if (order != null) {
                orderDAO.updateOrder(order.getId(), order.getUserID(), order.getDetails(), status);
            }
        }
    }
}
