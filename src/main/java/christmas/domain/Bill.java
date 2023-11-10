package christmas.domain;

import christmas.domain.food.*;

import java.util.*;

public class Bill {
    private static final int FRIDAY_MARK = 1;
    private static final int SATURDAY_MARK = 2;
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private static final int SPECIAL_DISCOUNT = 1000;

    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap();
    private int totalRegularPrice;
    private int weekdayDiscount;
    private int weekendDiscount;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void inputOrderMenu(List<Food> orderMenus) {
        for (Food menu : orderMenus) {
            int count = 1;
            if (orderMenu.get(menu) != null) {
                count = orderMenu.get(menu) + 1;
            }

            orderMenu.put(menu, count);
        }

        calculateTotalRegularPrice();
    }

    private void calculateTotalRegularPrice() {
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            for (int i = 1; i<= orderMenu.get(food); i++) {
                totalRegularPrice += food.getRegularPrice();
            }
        }
    }

    public int getTotalRegularPrice() {
        return totalRegularPrice;
    }

    public void calculateWeekdayDiscount() {
        weekdayDiscount = 0;
        if (isWeekend()) {
            return;
        }

        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Dessert) {
                weekdayDiscount += ((Dessert)food).getWeekdayDiscount() * orderMenu.get(food);
            }
        }
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    private boolean isWeekend() {
        return reservationDate % 7 == FRIDAY_MARK || reservationDate % 7 == SATURDAY_MARK;
    }

    public void calculateWeekendDiscount() {
        weekendDiscount = 0;
        if (!isWeekend()) {
            return;
        }

        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Main) {
                weekendDiscount += ((Main)food).getWeekendDiscount() * orderMenu.get(food);
            }
        }
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();
        if (match > 0) {
            return SPECIAL_DISCOUNT;
        }

        return 0;
    }
}
