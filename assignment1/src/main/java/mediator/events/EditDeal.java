package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;

public class EditDeal {

    public void editDeal(Integer id, Double price, String name, String type, Integer quantity) {
        DealDAO dealDAO = new DealDAOImpl();
        dealDAO.updateDeal(id, price, name, type, quantity);
    }
}
