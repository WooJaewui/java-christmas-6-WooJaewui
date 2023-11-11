package christmas.domain.event;

import christmas.domain.food.Dessert;
import christmas.domain.food.Food;

import java.util.Map;
import java.util.Set;

public class WeekdayDiscount implements WeekDiscount {
    private int discount;

    public int calculate(Map<Food, Integer> orderMenu) {
        discount = 0;
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Dessert) {
                discount -= ((Dessert)food).getWeekdayDiscount() * orderMenu.get(food);
            }
        }

        return discount;
    }

    public boolean isEvent(int reservationDate, Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        long desertCount = foods.stream().filter(food -> food instanceof Dessert).count();

        return !isWeekend(reservationDate) && desertCount > 0;
    }

    public int get() {
        return discount;
    }
}
