package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDealsByPriceAndType {

    public List<Deal> getFilteredDealsByPriceAndType(Double price, String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByPriceAndType(price, type);
    }
}
