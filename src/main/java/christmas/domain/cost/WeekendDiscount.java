package christmas.domain.cost;

import christmas.domain.food.Food;
import christmas.domain.food.Main;

import java.util.Map;
import java.util.Set;

public class WeekendDiscount implements WeekDiscount {
    private int discount;

    public int calculate(int reservationDate, Map<Food, Integer> orderMenu) {
        discount = 0;
        if (!isWeekend(reservationDate)) {
            return discount;
        }

        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Main) {
                discount += ((Main)food).getWeekendDiscount() * orderMenu.get(food);
            }
        }

        return discount;
    }

    public int get() {
        return discount;
    }
}
