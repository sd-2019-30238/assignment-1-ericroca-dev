package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDealsByPriceAndName {

    public List<Deal> getFilteredDealsByPriceAndName(Double price, String name) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByPriceAndName(price, name);
    }
}
