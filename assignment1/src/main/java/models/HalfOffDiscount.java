package models;

public class HalfOffDiscount implements Discount {

    @Override
    public Deal applyDiscount(Deal deal) {
        deal.setPrice(deal.getPrice() / 2);
        return deal;
    }
}
