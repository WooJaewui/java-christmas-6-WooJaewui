package christmas.domain;

public class Bill {
    private final int reservationDate;
    private final int christmasDiscount;
    private final int MAX_CHRISTMAS_DISCOUNT = 3400;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.christmasDiscount = calculateChristmasDiscount();
    }

    public int getChristmasDiscount() {
        if (reservationDate > 25) {
            return 0;
        }

        return christmasDiscount;
    }

    private int calculateChristmasDiscount() {
        return MAX_CHRISTMAS_DISCOUNT - ((25 - reservationDate) * 100);
    }
}
