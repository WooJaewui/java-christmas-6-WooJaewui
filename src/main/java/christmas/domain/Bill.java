package christmas.domain;

import christmas.domain.event.*;
import christmas.domain.food.*;
import christmas.domain.price.TotalBenefitPrice;
import christmas.domain.price.TotalRegularPrice;

import java.util.*;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap<>();
    private final TotalRegularPrice totalRegularPrice;
    private final TotalBenefitPrice totalBenefitPrice;
    private final ChristmasDiscount christmasDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final SpecialDiscount specialDiscount;
    private final GiveawayEvent giveawayCount;

    private Badge badge;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.totalBenefitPrice = new TotalBenefitPrice();
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

        calculatePriceAndEvent();
    }

    private void calculatePriceAndEvent() {
        totalRegularPrice.calculate(orderMenu);
        totalBenefitPrice.calculate(calculateEvent());
        badge = badge.calculate(totalBenefitPrice.get());
    }

    private int calculateEvent() {
        int benefitPrice = 0;
        if (isEvent()) {
            benefitPrice += christmasDiscount.calculate(reservationDate);
            benefitPrice += weekdayDiscount.calculate(reservationDate, orderMenu);
            benefitPrice += weekendDiscount.calculate(reservationDate, orderMenu);
            benefitPrice += specialDiscount.calculate(reservationDate);
            benefitPrice += giveawayCount.calculate(totalRegularPrice.get());
        }

        return benefitPrice;
    }

    private boolean isEvent() {
        return totalBenefitPrice.get() >= 10000;
    }
}
