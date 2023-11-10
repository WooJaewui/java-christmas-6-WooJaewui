package christmas.domain;

import christmas.domain.price.*;
import christmas.domain.food.*;

import java.util.*;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap();
    private final TotalRegularPrice totalRegularPrice;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final SpecialDiscount specialDiscount;
    private GiveawayEvent giveawayCount;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.weekdayDiscount = new WeekdayDiscount();
        this.weekendDiscount = new WeekendDiscount();
        this.specialDiscount = new SpecialDiscount();
        this.giveawayCount = new GiveawayEvent();
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
        giveawayCount.calculate(totalRegularPrice.get());
    }

    public int getGiveawayCount() {
        return giveawayCount.getCount();
    }
}
