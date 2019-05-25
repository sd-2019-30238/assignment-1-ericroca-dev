package business.services.read;

import business.interfaces.read.DealRead;
import data.implementation.DealDAOImpl;
import data.service.DealDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import models.Deal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealReadService implements DealRead {

    private Mediator mediator;

    public DealReadService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public List<Deal> getDeals() {
        return mediator.getDeals();
    }

    @Override
    public List<Deal> getFilteredDealsByPrice(Double price) {
        return mediator.getFilteredDealsByPrice(price);
    }

    @Override
    public List<Deal> getFilteredDealsByName(String name) {
        return mediator.getFilteredDealsByName(name);
    }

    @Override
    public List<Deal> getFilteredDealsByType(String type) {
        return mediator.getFilteredDealsByType(type);
    }

    @Override
    public List<Deal> getFilteredDealsByPriceAndName(Double price, String name) {
        return mediator.getFilteredDealsByPriceAndName(price, name);
    }

    @Override
    public List<Deal> getFilteredDealsByPriceAndType(Double price, String type) {
        return mediator.getFilteredDealsByPriceAndType(price, type);
    }

    @Override
    public List<Deal> getFilteredDealsByNameAndType(String name, String type) {
        return mediator.getFilteredDealsByNameAndType(name, type);
    }

    @Override
    public List<Deal> getFilteredDeals(Double price, String name, String type) {
        return mediator.getFilteredDeals(price, name, type);
    }
}
