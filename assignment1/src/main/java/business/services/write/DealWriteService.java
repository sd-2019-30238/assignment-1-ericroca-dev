package business.services.write;

import business.interfaces.write.DealWrite;
import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.Deal;
import models.Discount;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DealWriteService implements DealWrite {

    private Mediator mediator;

    public DealWriteService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public void addDeal(Double price, String name, String type, Integer quantity) {
        mediator.addDeal(price, name, type, quantity);
    }

    @Override
    public void editDeal(Integer id, Double price, String name, String type, Integer quantity) {
        mediator.editDeal(id, price, name, type, quantity);
    }

    @Override
    public void deleteDeal(Integer id) {
        mediator.deleteDeal(id);
    }

    @Override
    public void applyDiscount(Integer id, Discount discount) {
        mediator.applyDiscount(id, discount);
    }
}
