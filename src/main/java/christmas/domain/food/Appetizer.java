package christmas.domain.food;

public enum Appetizer implements Food {
    MUSHROOM_SOUP(6000),
    TAPAS(5500),
    CAESAR_SALAD(8000);

    private final int price;

    Appetizer(int price) {
        this.price = price;
    }

    @Override
    public int calculatePrice() {
        return price;
    }
}
