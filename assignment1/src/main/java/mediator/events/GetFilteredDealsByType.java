package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDealsByType {

    public List<Deal> getFilteredDealsByType(String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByType(type);
    }
}
