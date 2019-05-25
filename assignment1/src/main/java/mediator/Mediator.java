package mediator;

import models.Deal;
import models.Discount;

import java.util.List;

public interface Mediator {

    List<Deal> getUserCart(String username);

    void addToCart(String username, String name);

    void deleteItem(String username, String name);

    List<Deal> getDeals();

    List<Deal> getFilteredDealsByPrice(Double price);

    List<Deal> getFilteredDealsByName(String name);

    List<Deal> getFilteredDealsByType(String type);

    List<Deal> getFilteredDealsByPriceAndName(Double price, String name);

    List<Deal> getFilteredDealsByPriceAndType(Double price, String type);

    List<Deal> getFilteredDealsByNameAndType(String name, String type);

    List<Deal> getFilteredDeals(Double price, String name, String type);

    void addDeal(Double price, String name, String type, Integer quantity);

    void editDeal(Integer id, Double price, String name, String type, Integer quantity);

    void deleteDeal(Integer id);

    void applyDiscount(Integer id, Discount discount);

    String submitFeedback(Integer orderId, Integer userId, String details);
}
