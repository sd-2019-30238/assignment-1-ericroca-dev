package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDealsByPrice {

    public List<Deal> getFilteredDealsByPrice(Double price) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByPrice(price);
    }
}
