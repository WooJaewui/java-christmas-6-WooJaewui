package christmas.domain.food;

public enum Dessert implements Food {
    CHOCO_CAKE(15000),
    ICE_CREAM(5000);

    private final int price;
    private static final int WEEKDAY_DISCOUNT = 2023;

    Dessert(int price) {
        this.price = price;
    }

    @Override
    public int getRegularPrice() {
        return price;
    }

    public int getWeekdayDiscount() {
        return WEEKDAY_DISCOUNT;
    }
}
