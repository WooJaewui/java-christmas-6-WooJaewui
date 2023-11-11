package christmas.domain.cost;

import java.util.Arrays;

public class SpecialDiscount {
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private int discount;

    public int calculate(int reservationDate) {
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();
        discount = 0;
        if (match == 0) {
            return discount;
        }

        discount = 1000;
        return discount;
    }

    public int get() {
        return discount;
    }
}
