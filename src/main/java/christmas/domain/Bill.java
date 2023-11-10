package christmas.domain;

import christmas.domain.food.*;

import java.util.*;

public class Bill {
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private static final int SPECIAL_DISCOUNT = 1000;
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap();
    private final TotalRegularPrice totalRegularPrice;
    private final WeekdayDiscount weekdayDiscount;
    private WeekendDiscount weekendDiscount;
    private int giveawayCount;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.weekdayDiscount = new WeekdayDiscount();
        this.weekendDiscount = new WeekendDiscount();
    }

    public void inputOrderMenu(List<Food> orderMenus) {
        for (Food menu : orderMenus) {
            int count = 1;
            if (orderMenu.get(menu) != null) {
                count = orderMenu.get(menu) + 1;
            }

            orderMenu.put(menu, count);
        }

        totalRegularPrice.calculate(orderMenu);
    }

    public int getTotalRegularPrice() {
        return totalRegularPrice.get();
    }

    public void calculateWeekdayDiscount() {
        weekdayDiscount.calculate(reservationDate, orderMenu);
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount.get();
    }

    public void calculateWeekendDiscount() {
        weekendDiscount.calculate(reservationDate, orderMenu);
    }

    public int getWeekendDiscount() {
        return weekendDiscount.get();
    }

    public int getSpecialDiscount() {
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();
        if (match > 0) {
            return SPECIAL_DISCOUNT;
        }

        return 0;
    }

    public void calculateGiveawayCount() {
        giveawayCount = totalRegularPrice.get() / 120000;
    }

    public int getGiveawayCount() {
        return giveawayCount;
    }
}
