package christmas.domain.food;

public enum Dessert implements Food {
    CHOCO_CAKE(15000),
    ICE_CREAM(5000);

    private final int price;

    Dessert(int price) {
        this.price = price;
    }

    @Override
    public int calculatePrice() {
        return price;
    }
}
