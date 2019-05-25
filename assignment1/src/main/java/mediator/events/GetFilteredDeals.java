package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDeals {

    public List<Deal> getFilteredDeals(Double price, String name, String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByAll(price, name, type);
    }
}
