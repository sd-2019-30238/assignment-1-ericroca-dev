package models;

public class DiscountFactory {

    public Discount getDiscount(String discountType) {
        if (discountType == null) {
            return null;
        }

        if (discountType.equalsIgnoreCase("HALFOFF")) {
            return new HalfOffDiscount();
        }

        return null;
    }
}
