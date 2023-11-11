package christmas.domain.event;

public class ChristmasDiscount {
    private static final int MAX_DISCOUNT = -3400;
    private static final int CHRISTMAS_DAY = 25;
    private int discount;

    public int calculate(int reservationDate) {
        discount = MAX_DISCOUNT + (CHRISTMAS_DAY - reservationDate) * 100;
        return discount;
    }

    public boolean isEvent(int reservationDate) {
        return reservationDate > CHRISTMAS_DAY;
    }

    public int get() {
        return discount;
    }
}
