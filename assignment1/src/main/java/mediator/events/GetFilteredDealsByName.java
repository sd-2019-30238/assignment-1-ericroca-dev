package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDealsByName {

    public List<Deal> getFilteredDealsByName(String name) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByName(name);
    }
}
