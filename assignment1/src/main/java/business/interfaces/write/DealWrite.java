package business.interfaces.write;

import models.Discount;

public interface DealWrite {

    void addDeal(Double price, String name, String type, Integer quantity);

    void editDeal(Integer id, Double price, String name, String type, Integer quantity);

    void deleteDeal(Integer id);

    void applyDiscount(Integer id, Discount discount);
}
