package christmas.domain;

import christmas.domain.event.*;
import christmas.domain.food.*;
import christmas.domain.price.TotalBenefitPrice;
import christmas.domain.price.TotalRegularPrice;

import java.util.*;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap<>();
    private final Map<String, Integer> benefitDetails = new HashMap<>();
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
        if (notValidate(orderMenus)) {
            return;
        }
        for (Food menu : orderMenus) {
            int count = 1;
            if (orderMenu.get(menu) != null) {
                count = orderMenu.get(menu) + 1;
            }

            orderMenu.put(menu, count);
        }

        calculatePriceAndEvent();
    }

    private boolean notValidate(List<Food> orderMenus) {
        return orderMenus.stream().allMatch(food -> food instanceof Drink);
    }

    private void calculatePriceAndEvent() {
        totalRegularPrice.calculate(orderMenu);
        totalBenefitPrice.calculate(getTotalBenefitPrice());
        badge = badge.calculate(totalBenefitPrice.get());
    }

    private int getTotalBenefitPrice() {
        int benefitPrice = 0;
        if (isEvent()) {
            benefitPrice = calculateEvent(benefitPrice);
        }

        return benefitPrice;
    }

    private int calculateEvent(int benefitPrice) {
        if (christmasDiscount.isEvent(reservationDate)) {
            benefitPrice = calculateChristMasDiscount(benefitPrice);
        }

        if (weekdayDiscount.isEvent(reservationDate, orderMenu)) {
            benefitPrice = calculateWeekdayDiscount(benefitPrice);
        }

        if (weekendDiscount.isEvent(reservationDate, orderMenu)) {
            benefitPrice = calculateWeekendDiscount(benefitPrice);
        }

        if (specialDiscount.isEvent(reservationDate)) {
            benefitPrice = calculateSpecialDiscount(benefitPrice);
        }

        if (giveawayCount.isEvent(totalRegularPrice.get())) {
            benefitPrice = calculateGiveawayCount(benefitPrice);
        }

        return benefitPrice;
    }

    private boolean isEvent() {
        return totalBenefitPrice.get() >= 10000;
    }

    private int calculateChristMasDiscount(int benefitPrice) {
        benefitPrice += christmasDiscount.calculate(reservationDate);
        benefitDetails.put(christmasDiscount.getName(), christmasDiscount.getBenefit());

        return benefitPrice;
    }

    private int calculateWeekdayDiscount(int benefitPrice) {
        benefitPrice += weekdayDiscount.calculate(orderMenu);
        benefitDetails.put(weekdayDiscount.getName(), weekdayDiscount.getBenefit());

        return benefitPrice;
    }

    private int calculateWeekendDiscount(int benefitPrice) {
        benefitPrice += weekendDiscount.calculate(orderMenu);
        benefitDetails.put(weekendDiscount.getName(), weekendDiscount.getBenefit());

        return benefitPrice;
    }

    private int calculateSpecialDiscount(int benefitPrice) {
        benefitPrice += specialDiscount.calculate();
        benefitDetails.put(specialDiscount.getName(), specialDiscount.getBenefit());

        return benefitPrice;
    }

    private int calculateGiveawayCount(int benefitPrice) {
        benefitPrice += giveawayCount.calculate(totalRegularPrice.get());
        benefitDetails.put(giveawayCount.getName(), giveawayCount.getBenefit());

        return benefitPrice;
    }
}
