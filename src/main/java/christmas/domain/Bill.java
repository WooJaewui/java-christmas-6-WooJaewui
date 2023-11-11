package christmas.domain;

import christmas.domain.event.*;
import christmas.domain.food.*;
import christmas.domain.price.TotalRegularPrice;

import java.util.*;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap<>();
    private final TotalRegularPrice totalRegularPrice;
    private final ChristmasDiscount christmasDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final SpecialDiscount specialDiscount;
    private final GiveawayEvent giveawayCount;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.christmasDiscount = new ChristmasDiscount();
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

        calculateCost();
    }

    private void calculateCost() {
        totalRegularPrice.calculate(orderMenu);
        christmasDiscount.calculate(reservationDate);
        weekdayDiscount.calculate(reservationDate, orderMenu);
        weekendDiscount.calculate(reservationDate, orderMenu);
        specialDiscount.calculate(reservationDate);
        giveawayCount.calculate(totalRegularPrice.get());
    }
}
