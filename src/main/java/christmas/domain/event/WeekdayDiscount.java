package christmas.domain.event;

import christmas.domain.food.Dessert;
import christmas.domain.food.Food;

import java.util.Map;
import java.util.Set;

public class WeekdayDiscount implements WeekDiscount {
    private static final String NAME = "평일 할인";
    private int benefit;

    public int calculate(Map<Food, Integer> orderMenu) {
        benefit = 0;
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Dessert) {
                benefit -= ((Dessert)food).getWeekdayDiscount() * orderMenu.get(food);
            }
        }

        return benefit;
    }

    public boolean isEvent(int reservationDate, Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        long desertCount = foods.stream().filter(food -> food instanceof Dessert).count();

        return !isWeekend(reservationDate) && desertCount > 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getBenefit() {
        return benefit;
    }
}
