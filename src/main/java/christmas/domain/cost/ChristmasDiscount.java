package christmas.domain.cost;

public class ChristmasDiscount {
    private static final int MAX_DISCOUNT = 3400;
    private static final int CHRISTMAS_DAY = 25;
    private int discount;

    public int calculate(int reservationDate) {
        discount = 0;
        if (reservationDate > CHRISTMAS_DAY) {
            return discount;
        }

        discount = MAX_DISCOUNT - (CHRISTMAS_DAY - reservationDate) * 100;
        return discount;
    }

    public int get() {
        return discount;
    }
}
