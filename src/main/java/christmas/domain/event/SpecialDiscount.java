package christmas.domain.event;

import java.util.Arrays;

public class SpecialDiscount {
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private int discount;

    public int calculate() {
        discount = -1000;
        return discount;
    }

    public boolean isEvent(int reservationDate) {
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();
        return match > 0;
    }

    public int get() {
        return discount;
    }
}
