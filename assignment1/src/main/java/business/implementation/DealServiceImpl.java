package business.implementation;

import business.service.DealService;
import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;
import models.Discount;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
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

    @Override
    public void addDeal(Double price, String name, String type, Integer quantity) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
        Matcher nameMatcher = pattern.matcher(name);
        Matcher typeMatcher = pattern.matcher(type);

        if (nameMatcher.matches() && typeMatcher.matches() && price > 0 && quantity >= 0) {
            DealDAO dealDAO = new DealDAOImpl();
            dealDAO.addDeal(price, name, type, quantity);
        }
    }

    @Override
    public void editDeal(Integer id, Double price, String name, String type, Integer quantity) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
        Matcher nameMatcher = pattern.matcher(name);
        Matcher typeMatcher = pattern.matcher(type);

        if (id > 0 && nameMatcher.matches() && typeMatcher.matches() && price > 0 && quantity >= 0) {
            DealDAO dealDAO = new DealDAOImpl();
            dealDAO.updateDeal(id, price, name, type, quantity);
        }
    }

    @Override
    public void deleteDeal(Integer id) {
        if (id > 0) {
            DealDAO dealDAO = new DealDAOImpl();
            dealDAO.deleteDeal(id);
        }
    }

    @Override
    public void applyDiscount(Integer id, Discount discount) {
        if (id > 0) {
            DealDAO dealDAO = new DealDAOImpl();
            Deal deal = dealDAO.findByID(id);
            if (deal != null) {
                discount.applyDiscount(deal);
                dealDAO.updateDeal(deal.getId(), deal.getPrice(), deal.getName(),
                        deal.getType(), deal.getQuantity());
            }
        }
    }
}
