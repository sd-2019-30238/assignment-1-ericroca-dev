package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;

public class AddDeal {

    public void addDeal(Double price, String name, String type, Integer quantity) {
        DealDAO dealDAO = new DealDAOImpl();
        dealDAO.addDeal(price, name, type, quantity);
    }
}
