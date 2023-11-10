package christmas.domain.price;

import java.util.Arrays;

public class SpecialDiscount {
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private static final int DISCOUNT = 1000;

    public int get(int reservationDate) {
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();
        if (match > 0) {
            return DISCOUNT;
        }

        return 0;
    }
}
