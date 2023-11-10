package christmas.domain;

import christmas.domain.food.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap();
    private int totalRegularPrice;


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

    public boolean isWeekdayDiscount() {
        if (reservationDate % 7 == 1 || reservationDate % 7 == 2) {
            return true;
        }

        return false;
    }
}
