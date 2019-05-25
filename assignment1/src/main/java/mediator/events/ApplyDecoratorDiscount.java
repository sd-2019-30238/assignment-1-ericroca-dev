package mediator.events;

import models.ConcreteDiscountDecorator;
import models.Deal;
import models.DiscountType;
import models.HalfOffDiscount;

public class ApplyDecoratorDiscount {

    public void applyDecoratorDiscount(Deal deal) {
        ConcreteDiscountDecorator concreteDiscountDecorator = new ConcreteDiscountDecorator(new HalfOffDiscount(),
                DiscountType.HALFOFF);
        concreteDiscountDecorator.applyDiscount(deal);
    }
}
