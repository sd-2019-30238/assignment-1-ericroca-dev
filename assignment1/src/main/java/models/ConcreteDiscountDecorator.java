package models;

public class ConcreteDiscountDecorator extends DiscountDecorator {

    protected DiscountType discountType;

    public ConcreteDiscountDecorator(Discount decoratedDiscount, DiscountType discountType) {
        super(decoratedDiscount);
        this.discountType = discountType;
    }

    @Override
    public Deal applyDiscount(Deal deal) {
        System.out.println(discountType.toString() + " discount applied.");
        return decoratedDiscount.applyDiscount(deal);
    }
}
