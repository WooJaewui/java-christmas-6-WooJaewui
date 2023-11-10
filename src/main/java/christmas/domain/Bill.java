package christmas.domain;

import christmas.domain.price.SpecialDiscount;
import christmas.domain.price.TotalRegularPrice;
import christmas.domain.price.WeekdayDiscount;
import christmas.domain.price.WeekendDiscount;
import christmas.domain.food.*;

import java.util.*;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap();
    private final TotalRegularPrice totalRegularPrice;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final SpecialDiscount specialDiscount;

    private int giveawayCount;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.weekdayDiscount = new WeekdayDiscount();
        this.weekendDiscount = new WeekendDiscount();
        this.specialDiscount = new SpecialDiscount();
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
        return specialDiscount.get(reservationDate);
    }

    public void calculateGiveawayCount() {
        giveawayCount = totalRegularPrice.get() / 120000;
    }

    public int getGiveawayCount() {
        return giveawayCount;
    }
}
