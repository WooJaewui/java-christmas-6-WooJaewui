package christmas.domain.food;

public enum Drink implements Food {
    ZERO_COLA(3000),
    RED_WINE(60000),
    CHAMPAGNE(25000);

    private final int price;

    Drink(int price) {
        this.price = price;
    }

    @Override
    public int calculatePrice() {
        return price;
    }
}
