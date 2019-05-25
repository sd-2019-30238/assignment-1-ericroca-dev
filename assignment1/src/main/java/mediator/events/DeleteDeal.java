package mediator.events;

import data.implementation.DealDAOImpl;
import data.service.DealDAO;

public class DeleteDeal {

    public void deleteDeal(Integer id) {
        DealDAO dealDAO = new DealDAOImpl();
        dealDAO.deleteDeal(id);
    }
}
