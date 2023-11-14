package christmas.domain.food;

public enum Dessert implements Food {
    CHOCO_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    private final String name;
    private final int price;
    private static final int WEEKDAY_DISCOUNT = 2023;

    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRegularPrice() {
        return price;
    }

    public int getWeekdayDiscount() {
        return WEEKDAY_DISCOUNT;
    }
}
