package christmas.domain.event;

import christmas.domain.dto.EventDto;
import christmas.domain.food.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class WeekendEventTest {
    @Test
    void 주말할인_금액_테스트1() {
        WeekendEvent weekendDiscount = new WeekendEvent();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Main.CHRISTMAS_PASTA, 1);
        orderMenu.put(Main.SEAFOOD_PASTA, 1);

        int expectedBenefit = -4046;
        EventDto eventDto = new EventDto(1, 0, orderMenu);

        assertThat(weekendDiscount.isEvent(eventDto)).isTrue();
        assertThat(weekendDiscount.calculate(eventDto)).isEqualTo(expectedBenefit);
        assertThat(weekendDiscount.getBenefit()).isEqualTo(expectedBenefit);
    }

    @Test
    void 주말할인_금액_테스트2() {
        WeekendEvent weekendDiscount = new WeekendEvent();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,5);
        orderMenu.put(Main.T_BONE_STEAK,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        int expectedBenefit = -14161;
        EventDto eventDto = new EventDto(30, 0, orderMenu);

        assertThat(weekendDiscount.isEvent(eventDto)).isTrue();
        assertThat(weekendDiscount.calculate(eventDto)).isEqualTo(expectedBenefit);
        assertThat(weekendDiscount.getBenefit()).isEqualTo(expectedBenefit);
    }

    @Test
    void 주말할인_금액_테스트3() {
        WeekendEvent weekendDiscount = new WeekendEvent();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        int expectedBenefit = 0;
        EventDto eventDto = new EventDto(4, 0, orderMenu);

        assertThat(weekendDiscount.isEvent(eventDto)).isFalse();
        assertThat(weekendDiscount.calculate(eventDto)).isEqualTo(expectedBenefit);
        assertThat(weekendDiscount.getBenefit()).isEqualTo(expectedBenefit);
    }
}