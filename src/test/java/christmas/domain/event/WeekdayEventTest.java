package christmas.domain.event;

import christmas.domain.food.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class WeekdayEventTest {
    @Test
    void 평일할인_금액_테스트1() {
        WeekdayEvent weekdayDiscount = new WeekdayEvent();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.CHOCO_CAKE, 1);
        orderMenu.put(Dessert.ICE_CREAM, 1);

        if (weekdayDiscount.isEvent(3, orderMenu)) {
            weekdayDiscount.calculate(orderMenu);
        }

        int expectedPrice = -4046;
        Assertions.assertThat(weekdayDiscount.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 평일할인_금액_테스트2() {
        WeekdayEvent weekdayDiscount = new WeekdayEvent();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        if (weekdayDiscount.isEvent(17, orderMenu)) {
            weekdayDiscount.calculate(orderMenu);
        }

        int expectedPrice = -4046;
        Assertions.assertThat(weekdayDiscount.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 평일할인_금액_테스트3() {
        WeekdayEvent weekdayDiscount = new WeekdayEvent();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        if (weekdayDiscount.isEvent(1, orderMenu)) {
            weekdayDiscount.calculate(orderMenu);
        }

        int expectedPrice = 0;
        Assertions.assertThat(weekdayDiscount.getBenefit()).isEqualTo(expectedPrice);
    }
}