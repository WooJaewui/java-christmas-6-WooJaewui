package christmas.domain.service;

import christmas.domain.event.Badge;
import christmas.domain.food.Food;
import christmas.service.EventPlanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.event.Badge.*;
import static christmas.domain.food.Appetizer.MUSHROOM_SOUP;
import static christmas.domain.food.Dessert.CHOCO_CAKE;
import static christmas.domain.food.Drink.RED_WINE;
import static christmas.domain.food.Drink.ZERO_COLA;
import static christmas.domain.food.Main.BARBECUE_RIBS;
import static org.assertj.core.api.Assertions.assertThat;

class EventPlannerTest {
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31})
    void 주문일자_테스트(int reservationDate) {
        EventPlanner bill = new EventPlanner(reservationDate);

        assertThat(bill.getReservationDate()).isEqualTo(reservationDate);
    }

    @Test
    void 주문내역_테스트() {
        EventPlanner bill = new EventPlanner(10);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(CHOCO_CAKE, 3);
        orderMenu.put(BARBECUE_RIBS,2);
        orderMenu.put(RED_WINE, 1);
        orderMenu.put(MUSHROOM_SOUP, 1);

        bill.inputOrderMenu(orderMenu);

        Map<Food, Integer> expectedOrderMenu = bill.getOrderMenu();
        assertThat(expectedOrderMenu.get(CHOCO_CAKE)).isEqualTo(3);
        assertThat(expectedOrderMenu.get(BARBECUE_RIBS)).isEqualTo(2);
        assertThat(expectedOrderMenu.get(RED_WINE)).isEqualTo(1);
        assertThat(expectedOrderMenu.get(MUSHROOM_SOUP)).isEqualTo(1);
        assertThat(expectedOrderMenu.get(ZERO_COLA)).isNull();
    }

    @Test
    void 할인전_총주문금액_테스트() {
        EventPlanner bill = new EventPlanner(10);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(CHOCO_CAKE, 3);
        orderMenu.put(BARBECUE_RIBS,2);
        orderMenu.put(RED_WINE, 1);
        orderMenu.put(MUSHROOM_SOUP, 1);

        bill.inputOrderMenu(orderMenu);

        int expectedTotalRegularPrice = 219000;
        assertThat(bill.getTotalRegularPrice()).isEqualTo(expectedTotalRegularPrice);
    }

    @Test
    void 할인후_배지_테스트1() {
        EventPlanner eventPlanner = new EventPlanner(10);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(CHOCO_CAKE, 3);
        orderMenu.put(BARBECUE_RIBS,2);
        orderMenu.put(RED_WINE, 1);
        orderMenu.put(MUSHROOM_SOUP, 1);

        eventPlanner.inputOrderMenu(orderMenu);

        assertThat(eventPlanner.getBadge()).isEqualTo(SANTA);
    }

    @Test
    void 할인후_배지_테스트2() {
        EventPlanner eventPlanner = new EventPlanner(3);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(CHOCO_CAKE, 3);

        eventPlanner.inputOrderMenu(orderMenu);

        assertThat(eventPlanner.getBadge()).isEqualTo(STAR);
    }
}