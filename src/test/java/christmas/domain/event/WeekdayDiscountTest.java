package christmas.domain.event;

import christmas.domain.food.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class WeekdayDiscountTest {
    @Test
    void 평일할인_금액_테스트1() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.CHOCO_CAKE, 1);
        orderMenu.put(Dessert.ICE_CREAM, 1);

        weekdayDiscount.calculate(3, orderMenu);

        int expectedPrice = 4046;

        Assertions.assertThat(weekdayDiscount.get()).isEqualTo(expectedPrice);
    }

    @Test
    void 평일할인_금액_테스트2() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        weekdayDiscount.calculate(17, orderMenu);

        int expectedPrice = 4046;

        Assertions.assertThat(weekdayDiscount.get()).isEqualTo(expectedPrice);
    }

    @Test
    void 평일할인_금액_테스트3() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        weekdayDiscount.calculate(1, orderMenu);

        int expectedPrice = 0;

        Assertions.assertThat(weekdayDiscount.get()).isEqualTo(expectedPrice);
    }

}