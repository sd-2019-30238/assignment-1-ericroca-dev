package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.ArrayList;
import java.util.List;

public class GetCartDeals {

    public List<Deal> getCartDeals(List<String> names) {
        DealDAO dealDAO = new DealDAOImpl();
        List<Deal> dealList = new ArrayList<>();
        for (int i = 0; i < names.size(); ++i) {
            dealList.add(dealDAO.findByName(names.get(i)));

            if (dealList.get(i).getQuantity() == 0) {
                return null;
            }
        }
        return dealList;
    }
}
