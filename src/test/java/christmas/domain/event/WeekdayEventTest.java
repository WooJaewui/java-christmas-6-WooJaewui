package christmas.domain.event;

import christmas.domain.dto.EventDto;
import christmas.domain.food.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class WeekdayEventTest {
    @Test
    void 평일할인_금액_테스트1() {
        WeekdayEvent weekdayDiscount = new WeekdayEvent();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.CHOCO_CAKE, 1);
        orderMenu.put(Dessert.ICE_CREAM, 1);

        int expectedBenefit = -4046;
        EventDto eventDto = new EventDto(3, 0, orderMenu);

        assertThat(weekdayDiscount.isEvent(eventDto)).isTrue();
        assertThat(weekdayDiscount.calculate(eventDto)).isEqualTo(expectedBenefit);
        assertThat(weekdayDiscount.getBenefit()).isEqualTo(expectedBenefit);
    }

    @Test
    void 평일할인_금액_테스트2() {
        WeekdayEvent weekdayDiscount = new WeekdayEvent();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        int expectedBenefit = -4046;
        EventDto eventDto = new EventDto(17, 0, orderMenu);

        assertThat(weekdayDiscount.isEvent(eventDto)).isTrue();
        assertThat(weekdayDiscount.calculate(eventDto)).isEqualTo(expectedBenefit);
        assertThat(weekdayDiscount.getBenefit()).isEqualTo(expectedBenefit);
    }

    @Test
    void 평일할인_금액_테스트3() {
        WeekdayEvent weekdayDiscount = new WeekdayEvent();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        int expectedBenefit = 0;
        EventDto eventDto = new EventDto(1, 0, orderMenu);

        assertThat(weekdayDiscount.isEvent(eventDto)).isFalse();
        assertThat(weekdayDiscount.calculate(eventDto)).isEqualTo(expectedBenefit);
        assertThat(weekdayDiscount.getBenefit()).isEqualTo(expectedBenefit);
    }
}