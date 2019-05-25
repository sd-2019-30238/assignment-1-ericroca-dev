package mediator.handlers;

import mediator.events.*;
import models.Deal;
import models.Discount;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealHandler implements Handler {

    private Integer id;
    private Double price;
    private String name;
    private String type;
    private Integer quantity;
    private Discount discount;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public <T> T handle(Request request) {
        if (request == Request.GETDEALS) {
            List<Deal> dealList = new GetDeals().getDeals();
            return (T) dealList;
        } else if (request == Request.GETDEALSBYPRICE) {
            List<Deal> dealList = new GetFilteredDealsByPrice().getFilteredDealsByPrice(price);
            return (T) dealList;
        } else if (request == Request.GETDEALSBYNAME) {
            List<Deal> dealList = new GetFilteredDealsByName().getFilteredDealsByName(name);
            return (T) dealList;
        } else if (request == Request.GETDEALSBYTYPE) {
            List<Deal> dealList = new GetFilteredDealsByType().getFilteredDealsByType(type);
            return (T) dealList;
        } else if (request == Request.GETDEALSBYPRICEANDNAME) {
            List<Deal> dealList = new GetFilteredDealsByPriceAndName().getFilteredDealsByPriceAndName(price, name);
            return (T) dealList;
        } else if (request == Request.GETDEALSBYPRICEANDTYPE) {
            List<Deal> dealList = new GetFilteredDealsByPriceAndType().getFilteredDealsByPriceAndType(price, type);
            return (T) dealList;
        } else if (request == Request.GETDEALSBYNAMEANDTYPE) {
            List<Deal> dealList = new GetFilteredDealsByNameAndType().getFilteredDealsByNameAndType(name, type);
            return (T) dealList;
        } else if (request == Request.GETFILTEREDDEALS) {
            List<Deal> dealList = new GetFilteredDeals().getFilteredDeals(price, name, type);
            return (T) dealList;
        } else if (request == Request.ADDDEAL) {
            Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
            Matcher nameMatcher = pattern.matcher(name);
            Matcher typeMatcher = pattern.matcher(type);

            if (nameMatcher.matches() && typeMatcher.matches() && price > 0 && quantity >= 0) {
                new AddDeal().addDeal(price, name, type, quantity);
            }
        } else if (request == Request.EDITDEAL) {
            Pattern pattern = Pattern.compile("^[a-z0-9_-]{1,45}$");
            Matcher nameMatcher = pattern.matcher(name);
            Matcher typeMatcher = pattern.matcher(type);

            if (id > 0 && nameMatcher.matches() && typeMatcher.matches() && price > 0 && quantity >= 0) {
                new EditDeal().editDeal(id, price, name, type, quantity);
            }
        } else if (request == Request.DELETEDEAL) {
            if (id > 0) {
                new DeleteDeal().deleteDeal(id);
            }
        } else if (request == Request.APPLYDISCOUNT) {
            if (id > 0) {
                Deal deal = new FindDealByID().findDealByID(id);
                if (deal != null) {
                    new ApplyDiscount().applyDiscount(deal, discount);
                    new EditDeal().editDeal(deal.getId(), deal.getPrice(), deal.getName(), deal.getType(),
                            deal.getQuantity());
                }
            }
        }
        return null;
    }
}
