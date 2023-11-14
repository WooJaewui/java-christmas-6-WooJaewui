package christmas.domain.food;

public enum Main implements Food {
    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private final String name;
    private final int price;
    private static final int WEEKEND_DISCOUNT = 2023;

    Main(String name, int price) {
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

    public int getWeekendDiscount() {
        return WEEKEND_DISCOUNT;
    }
}
