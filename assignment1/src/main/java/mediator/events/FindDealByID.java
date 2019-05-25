package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

public class FindDealByID {

    public Deal findDealByID (Integer id) {
        DealDAO dealDAO = new DealDAOImpl();
        Deal deal = dealDAO.findByID(id);
        return deal;
    }
}
