package christmas.service;

import christmas.domain.event.ChristmasEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.Event;
import christmas.domain.food.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.event.Badge.SANTA;
import static christmas.domain.event.Badge.STAR;
import static christmas.domain.food.Appetizer.*;
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

    @Test
    void 할인내역_테스트1() {
        EventPlanner eventPlanner = new EventPlanner(3);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(CAESAR_SALAD, 1);

        eventPlanner.inputOrderMenu(orderMenu);
        List<Event> events = eventPlanner.getEvents();

        assertThat(events.isEmpty()).isTrue();
    }

    @Test
    void 할인내역_테스트2() {
        EventPlanner eventPlanner = new EventPlanner(25);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(MUSHROOM_SOUP, 3);

        eventPlanner.inputOrderMenu(orderMenu);
        List<Event> events = eventPlanner.getEvents();

        assertThat(events.get(0).getName()).isEqualTo(new ChristmasEvent().getName());
        assertThat(events.get(1).getName()).isEqualTo(new SpecialEvent().getName());
    }

    @Test
    void 결제금액_테스트1() {
        EventPlanner eventPlanner = new EventPlanner(25);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(MUSHROOM_SOUP, 3);

        eventPlanner.inputOrderMenu(orderMenu);

        assertThat(eventPlanner.getPaymentPrice()).isEqualTo(13600);
    }

    @Test
    void 결제금액_테스트2() {
        EventPlanner eventPlanner = new EventPlanner(25);

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(TAPAS, 2);
        orderMenu.put(BARBECUE_RIBS, 2);
        orderMenu.put(CHOCO_CAKE, 2);
        orderMenu.put(RED_WINE, 1);

        eventPlanner.inputOrderMenu(orderMenu);

        assertThat(eventPlanner.getPaymentPrice()).isEqualTo(200554);
    }
}