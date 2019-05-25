package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetFilteredDealsByNameAndType {

    public List<Deal> getFilteredDealsByNameAndType(String name, String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByNameAndType(name, type);
    }
}
