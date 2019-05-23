package business.services.write;

import business.interfaces.write.DealWrite;
import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import models.Deal;
import models.Discount;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DealWriteService implements DealWrite {

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
