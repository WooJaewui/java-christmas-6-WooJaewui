package christmas.domain.event;

import christmas.domain.food.Food;
import christmas.domain.food.Main;

import java.util.Map;
import java.util.Set;

public class WeekendEvent implements WeekEvent {
    private static final String NAME = "주말 할인";
    private int benefit;

    public int calculate(Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Main) {
                benefit -= ((Main)food).getWeekendDiscount() * orderMenu.get(food);
            }
        }

        return benefit;
    }

    public boolean isEvent(int reservationDate, Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        long mainCount = foods.stream().filter(food -> food instanceof Main).count();

        return isWeekend(reservationDate) && mainCount > 0;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getBenefit() {
        return benefit;
    }
}
