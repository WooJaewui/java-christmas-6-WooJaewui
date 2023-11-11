package christmas.domain.event;

import christmas.domain.food.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class WeekendDiscountTest {
    @Test
    void 주말할인_금액_테스트1() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Main.CHRISTMAS_PASTA, 1);
        orderMenu.put(Main.SEAFOOD_PASTA, 1);

        if (weekendDiscount.isEvent(1, orderMenu)) {
            weekendDiscount.calculate(orderMenu);
        }


        int expectedPrice = -4046;
        Assertions.assertThat(weekendDiscount.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 주말할인_금액_테스트2() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,5);
        orderMenu.put(Main.T_BONE_STEAK,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        if (weekendDiscount.isEvent(30, orderMenu)) {
            weekendDiscount.calculate(orderMenu);
        }

        int expectedPrice = -14161;
        Assertions.assertThat(weekendDiscount.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 주말할인_금액_테스트3() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        if (weekendDiscount.isEvent(4, orderMenu)) {
            weekendDiscount.calculate(orderMenu);
        }

        int expectedPrice = 0;
        Assertions.assertThat(weekendDiscount.getBenefit()).isEqualTo(expectedPrice);
    }
}