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
    private final ChristmasEvent christmasEvent;
    private final WeekdayEvent weekdayEvent;
    private final WeekendEvent weekendEvent;
    private final SpecialEvent specialEvent;
    private final ChampaignGiveawayEvent giveawayEvent;
    private Badge badge;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.totalBenefitPrice = new TotalBenefitPrice();
        this.christmasEvent = new ChristmasEvent();
        this.weekdayEvent = new WeekdayEvent();
        this.weekendEvent = new WeekendEvent();
        this.specialEvent = new SpecialEvent();
        this.giveawayEvent = new ChampaignGiveawayEvent();
    }

    public void inputOrderMenu(List<Food> orderMenus) {
        if (validate(orderMenus)) {
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

    private boolean validate(List<Food> orderMenus) {
        return orderMenus.stream().allMatch(food -> food instanceof Drink);
    }

    private void calculatePriceAndEvent() {
        totalRegularPrice.calculate(orderMenu);
        /*totalBenefitPrice.calculate(getTotalBenefitPrice());
        badge = badge.calculate(totalBenefitPrice.get());*/
    }

    /*private int getTotalBenefitPrice() {
        int benefitPrice = 0;
        if (isEvent()) {
            benefitPrice = calculateEvent(benefitPrice);
        }

        return benefitPrice;
    }

    private boolean isEvent() {
        return totalBenefitPrice.get() >= 10000;
    }

    private int calculateEvent(int benefitPrice) {
        if (christmasEvent.isEvent(reservationDate)) {
            benefitPrice = calculateChristMasEvent(benefitPrice);
        }

        if (weekdayEvent.isEvent(reservationDate, orderMenu)) {
            benefitPrice = calculateWeekdayDiscount(benefitPrice);
        }

        if (weekendEvent.isEvent(reservationDate, orderMenu)) {
            benefitPrice = calculateWeekendDiscount(benefitPrice);
        }

        if (specialEvent.isEvent(reservationDate)) {
            benefitPrice = calculateSpecialDiscount(benefitPrice);
        }

        if (giveawayEvent.isEvent(totalRegularPrice.get())) {
            benefitPrice = calculateGiveawayCount(benefitPrice);
        }

        return benefitPrice;
    }

    private int calculateChristMasEvent(int benefitPrice) {
        benefitPrice += christmasEvent.calculate(reservationDate);
        benefitDetails.put(christmasEvent.getName(), christmasEvent.getBenefit());

        return benefitPrice;
    }

    private int calculateWeekdayDiscount(int benefitPrice) {
        benefitPrice += weekdayEvent.calculate(orderMenu);
        benefitDetails.put(weekdayEvent.getName(), weekdayEvent.getBenefit());

        return benefitPrice;
    }

    private int calculateWeekendDiscount(int benefitPrice) {
        benefitPrice += weekendEvent.calculate(orderMenu);
        benefitDetails.put(weekendEvent.getName(), weekendEvent.getBenefit());

        return benefitPrice;
    }

    private int calculateSpecialDiscount(int benefitPrice) {
        benefitPrice += specialEvent.calculate();
        benefitDetails.put(specialEvent.getName(), specialEvent.getBenefit());

        return benefitPrice;
    }

    private int calculateGiveawayCount(int benefitPrice) {
        benefitPrice += giveawayEvent.calculate(totalRegularPrice.get());
        benefitDetails.put(giveawayEvent.getName(), giveawayEvent.getBenefit());

        return benefitPrice;
    }*/
}
