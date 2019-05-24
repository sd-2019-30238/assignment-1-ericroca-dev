package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

public class FindDeal {

    public Deal findDeal(String name) {
        DealDAO dealDAO = new DealDAOImpl();
        Deal deal = dealDAO.findByName(name);
        return deal;
    }
}
