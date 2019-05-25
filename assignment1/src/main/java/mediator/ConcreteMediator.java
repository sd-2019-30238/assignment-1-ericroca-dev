package mediator;

import mediator.handlers.CartHandler;
import mediator.handlers.DealHandler;
import mediator.handlers.Handler;
import mediator.handlers.Request;
import models.Deal;
import models.Discount;

import java.util.List;

public class ConcreteMediator implements Mediator {

    @Override
    public List<Deal> getUserCart(String username) {
        Handler handler = new CartHandler();
        ((CartHandler) handler).setUsername(username);
        return handler.handle(Request.GETUSERCART);
    }

    @Override
    public void addToCart(String username, String name) {
        Handler handler = new CartHandler();
        ((CartHandler) handler).setUsername(username);
        ((CartHandler) handler).setDeal(name);
        handler.handle(Request.ADDTOCART);
    }

    @Override
    public void deleteItem(String username, String name) {
        Handler handler = new CartHandler();
        ((CartHandler) handler).setUsername(username);
        ((CartHandler) handler).setDeal(name);
        handler.handle(Request.DELETEITEM);
    }

    @Override
    public List<Deal> getDeals() {
        Handler handler = new DealHandler();
        return handler.handle(Request.GETDEALS);
    }

    @Override
    public List<Deal> getFilteredDealsByPrice(Double price) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setPrice(price);
        return handler.handle(Request.GETDEALSBYPRICE);
    }

    @Override
    public List<Deal> getFilteredDealsByName(String name) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setName(name);
        return handler.handle(Request.GETDEALSBYNAME);
    }

    @Override
    public List<Deal> getFilteredDealsByType(String type) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setType(type);
        return handler.handle(Request.GETDEALSBYTYPE);
    }

    @Override
    public List<Deal> getFilteredDealsByPriceAndName(Double price, String name) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setPrice(price);
        ((DealHandler) handler).setName(name);
        return handler.handle(Request.GETDEALSBYPRICEANDNAME);
    }

    @Override
    public List<Deal> getFilteredDealsByPriceAndType(Double price, String type) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setPrice(price);
        ((DealHandler) handler).setType(type);
        return handler.handle(Request.GETDEALSBYPRICEANDTYPE);
    }

    @Override
    public List<Deal> getFilteredDealsByNameAndType(String name, String type) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setName(name);
        ((DealHandler) handler).setType(type);
        return handler.handle(Request.GETDEALSBYNAMEANDTYPE);
    }

    @Override
    public List<Deal> getFilteredDeals(Double price, String name, String type) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setPrice(price);
        ((DealHandler) handler).setName(name);
        ((DealHandler) handler).setType(type);
        return handler.handle(Request.GETFILTEREDDEALS);
    }

    @Override
    public void addDeal(Double price, String name, String type, Integer quantity) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setPrice(price);
        ((DealHandler) handler).setName(name);
        ((DealHandler) handler).setType(type);
        ((DealHandler) handler).setQuantity(quantity);
        handler.handle(Request.ADDDEAL);
    }

    @Override
    public void editDeal(Integer id, Double price, String name, String type, Integer quantity) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setId(id);
        ((DealHandler) handler).setPrice(price);
        ((DealHandler) handler).setName(name);
        ((DealHandler) handler).setType(type);
        ((DealHandler) handler).setQuantity(quantity);
        handler.handle(Request.EDITDEAL);
    }

    @Override
    public void deleteDeal(Integer id) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setId(id);
        handler.handle(Request.DELETEDEAL);
    }

    @Override
    public void applyDiscount(Integer id, Discount discount) {
        Handler handler = new DealHandler();
        ((DealHandler) handler).setId(id);
        ((DealHandler) handler).setDiscount(discount);
        handler.handle(Request.APPLYDISCOUNT);
    }
}
