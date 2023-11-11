package christmas.domain.event;

public class ChristmasDiscount implements Event {
    private static final int MAX_DISCOUNT = -3400;
    private static final int CHRISTMAS_DAY = 25;
    private static final String NAME = "크리스마스 디데이 할인";
    private int benefit;

    public int calculate(int reservationDate) {
        benefit = MAX_DISCOUNT + (CHRISTMAS_DAY - reservationDate) * 100;
        return benefit;
    }

    public boolean isEvent(int reservationDate) {
        return reservationDate > CHRISTMAS_DAY;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getBenefit() {
        return benefit;
    }
}
