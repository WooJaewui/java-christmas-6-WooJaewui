package christmas.domain;

public class Bill {
    private final int reservationDate;
    private final int MAX_CHRISTMAS_DISCOUNT = 3400;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getChristmasDiscount() {
        if (reservationDate > 25) {
            return 0;
        }

        return calculateChristmasDiscount();
    }

    private int calculateChristmasDiscount() {
        return MAX_CHRISTMAS_DISCOUNT - ((25 - reservationDate) * 100);
    }
}
