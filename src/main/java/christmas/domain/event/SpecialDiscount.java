package christmas.domain.event;

import java.util.Arrays;

public class SpecialDiscount implements Event {
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private static final String NAME = "특별 할인";
    private int benefit;

    public int calculate() {
        benefit = -1000;
        return benefit;
    }

    public boolean isEvent(int reservationDate) {
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();
        return match > 0;
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
