package business.implementation;

import business.service.DealService;
import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;

import java.util.List;

public class DealServiceImpl implements DealService {

    @Override
    public List<Deal> getDeals() {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDeals();
    }
}
