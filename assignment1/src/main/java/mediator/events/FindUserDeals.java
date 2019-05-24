package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Cart;
import models.Deal;

import java.util.ArrayList;
import java.util.List;

public class FindUserDeals {

    public List<Deal> findUserDeals(List<Cart> cartList) {
        DealDAO dealDAO = new DealDAOImpl();
        List<Deal> dealList = new ArrayList<>();
        for (Cart cart : cartList) {
            dealList.add(dealDAO.findByID(cart.getDealID()));
        }
        return dealList;
    }
}
