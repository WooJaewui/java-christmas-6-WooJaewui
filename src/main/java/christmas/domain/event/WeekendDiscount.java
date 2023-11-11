package christmas.domain.event;

import christmas.domain.food.Dessert;
import christmas.domain.food.Food;
import christmas.domain.food.Main;

import java.util.Map;
import java.util.Set;

public class WeekendDiscount implements WeekDiscount {
    private int discount;

    public int calculate(Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Main) {
                discount -= ((Main)food).getWeekendDiscount() * orderMenu.get(food);
            }
        }

        return discount;
    }

    public boolean isEvent(int reservationDate, Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        long mainCount = foods.stream().filter(food -> food instanceof Main).count();

        return isWeekend(reservationDate) && mainCount > 0;
    }

    public int get() {
        return discount;
    }

}
