package christmas.domain.price;

import christmas.domain.food.Food;

import java.util.Map;
import java.util.Set;

public class TotalRegularPrice {
    private int price;

    public int calculate(Map<Food, Integer> orderMenu) {
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            for (int i = 1; i<= orderMenu.get(food); i++) {
                price += food.getRegularPrice();
            }
        }

        return price;
    }

    public int get() {
        return price;
    }
}
