package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class GetDeals {

    public List<Deal> getDeals() {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDeals();
    }
}
