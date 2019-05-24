package models;

public abstract class DiscountDecorator implements Discount {

    protected Discount decoratedDiscount;

    public DiscountDecorator(Discount decoratedDiscount) {
        super();
        this.decoratedDiscount = decoratedDiscount;
    }
}
