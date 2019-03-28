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

    @Override
    public List<Deal> getFilteredDealsByPrice(Double price) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByPrice(price);
    }

    @Override
    public List<Deal> getFilteredDealsByName(String name) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByName(name);
    }

    @Override
    public List<Deal> getFilteredDealsByType(String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByType(type);
    }

    @Override
    public List<Deal> getFilteredDealsByPriceAndName(Double price, String name) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByPriceAndName(price, name);
    }

    @Override
    public List<Deal> getFilteredDealsByPriceAndType(Double price, String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByPriceAndType(price, type);
    }

    @Override
    public List<Deal> getFilteredDealsByNameAndType(String name, String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByNameAndType(name, type);
    }

    @Override
    public List<Deal> getFilteredDeals(Double price, String name, String type) {
        DealDAO dealDAO = new DealDAOImpl();
        return dealDAO.getDealsByAll(price, name, type);
    }
}
