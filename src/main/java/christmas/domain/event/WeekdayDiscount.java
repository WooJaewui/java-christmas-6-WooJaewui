package christmas.domain.event;

import christmas.domain.food.Dessert;
import christmas.domain.food.Food;

import java.util.Map;
import java.util.Set;

public class WeekdayDiscount implements WeekDiscount {
    private int discount;

    public int calculate(int reservationDate, Map<Food, Integer> orderMenu) {
        discount = 0;
        if (isWeekend(reservationDate)) {
            return discount;
        }

        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Dessert) {
                discount += ((Dessert)food).getWeekdayDiscount() * orderMenu.get(food);
            }
        }

        return discount;
    }

    public int get() {
        return discount;
    }
}
