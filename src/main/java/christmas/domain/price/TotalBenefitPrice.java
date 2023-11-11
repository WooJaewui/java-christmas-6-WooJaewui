package christmas.domain.price;

import christmas.domain.food.Food;

import java.util.Map;
import java.util.Set;

public class TotalBenefitPrice {
    private int price;

    public int calculate(int totalBenefitPrice) {
        this.price = totalBenefitPrice;

        return price;
    }

    public int get() {
        return price;
    }
}
