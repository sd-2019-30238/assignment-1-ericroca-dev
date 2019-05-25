package mediator.events;

import models.Deal;
import models.Discount;

public class ApplyDiscount {

    public void applyDiscount(Deal deal, Discount discount) {
        discount.applyDiscount(deal);
    }
}
