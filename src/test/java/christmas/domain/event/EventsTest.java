package christmas.domain.event;

import christmas.domain.dto.EventDto;
import christmas.domain.food.*;
import christmas.domain.price.TotalRegularPrice;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.event.Badge.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventsTest {
    @Test
    void 이벤트_총주문금액이하_테스트() {
        Events events = new Events();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        int expectedTotalBenefitPrice = 0;
        EventDto eventDto = new EventDto(1, 9999, orderMenu);
        events.calculateEvent(eventDto);

        assertThat(events.getTotalBenefitPrice()).isEqualTo(expectedTotalBenefitPrice);
    }

    @Test
    void 이벤트_총혜택금액_테스트1() {
        Events events = new Events();

        /*
         *  할인 전 총주문 금액 : 68000
         *
         *  아이스크림 : 5000
         *  바베큐립 : 54000
         *  양송이스프 : 6000
         *  제로콜라 : 3000
         */
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 1);                    // 5000
        orderMenu.put(Main.BARBECUE_RIBS,1);                    // 54000
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);              // 6000
        orderMenu.put(Drink.ZERO_COLA, 1);                      // 3000

        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        EventDto eventDto = new EventDto(1, totalRegularPrice.calculate(orderMenu), orderMenu);
        events.calculateEvent(eventDto);

        /*
         *  총혜택 금액 : -3023
         *
         *  크리스마스 할인    : -1000
         *  주말 할인         : -2023
         */
        int expectedTotalBenefitPrice = -3023;

        assertThat(events.getTotalBenefitPrice()).isEqualTo(expectedTotalBenefitPrice);
    }

    @Test
    void 이벤트_총혜택금액_테스트2() {
        Events events = new Events();

        /*
         *  할인 전 총주문 금액 : 127000
         *
         *  아이스크림 : 10000
         *  바베큐립 : 108000
         *  양송이스프: 6000
         *  제로콜라 : 3000
         */
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);                    // 10000
        orderMenu.put(Main.BARBECUE_RIBS,2);                    // 108000
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);              // 6000
        orderMenu.put(Drink.ZERO_COLA, 1);                      // 3000

        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        EventDto eventDto = new EventDto(24, totalRegularPrice.calculate(orderMenu), orderMenu);
        events.calculateEvent(eventDto);

        /*
         *  총혜택 금액 : -33346
         *
         *  크리스마스 할인 : -3300
         *  평일 할인 : -4046
         *  특별 할인 : -1000
         *  증정 이벤트 : -25000
         */
        int expectedTotalBenefitPrice = -33346;

        assertThat(events.getTotalBenefitPrice()).isEqualTo(expectedTotalBenefitPrice);
    }

    @Test
    void 이벤트_배지_테스트1() {
        Events events = new Events();

        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 2);
        orderMenu.put(Drink.RED_WINE, 2);

        EventDto eventDto = new EventDto(1, 9999, orderMenu);
        events.calculateEvent(eventDto);

        assertThat(events.getBadge()).isEqualTo(NONE);
    }

    @Test
    void 이벤트_배지_테스트2() {
        Events events = new Events();

        /*
         *  할인 전 총주문 금액 : 93000
         *
         *  아이스크림 : 5000
         *  바베큐립 : 54000
         *  크리스마스 파스타 : 25000
         *  양송이스프 : 6000
         *  제로콜라 : 3000
         */
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 1);                    // 5000
        orderMenu.put(Main.BARBECUE_RIBS,1);                    // 54000
        orderMenu.put(Main.CHRISTMAS_PASTA,1);                  // 25000
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);              // 6000
        orderMenu.put(Drink.ZERO_COLA, 1);                      // 3000

        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        EventDto eventDto = new EventDto(1, totalRegularPrice.calculate(orderMenu), orderMenu);
        events.calculateEvent(eventDto);

        /*
         *  총혜택 금액 : -5046
         *
         *  크리스마스 할인    : -1000
         *  주말 할인         : -4046
         */
        assertThat(events.getBadge()).isEqualTo(STAR);
    }

    @Test
    void 이벤트_배지_테스트3() {
        Events events = new Events();

        /*
         *  할인 전 총주문 금액 : 127000
         *
         *  아이스크림 : 10000
         *  바베큐립 : 108000
         *  양송이스프: 6000
         *  제로콜라 : 3000
         */
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);                    // 10000
        orderMenu.put(Main.BARBECUE_RIBS,2);                    // 108000
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);              // 6000
        orderMenu.put(Drink.ZERO_COLA, 1);                      // 3000

        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        EventDto eventDto = new EventDto(24, totalRegularPrice.calculate(orderMenu), orderMenu);
        events.calculateEvent(eventDto);

        /*
         *  총혜택 금액 : -33346
         *
         *  크리스마스 할인 : -3300
         *  평일 할인 : -4046
         *  특별 할인 : -1000
         *  증정 이벤트 : -25000
         */
        assertThat(events.getBadge()).isEqualTo(SANTA);
    }

    @Test
    void 이벤트_개별금액_테스트1() {
        Events events = new Events();

        /*
         *  할인 전 총주문 금액 : 68000
         *
         *  아이스크림 : 5000
         *  바베큐립 : 54000
         *  양송이스프 : 6000
         *  제로콜라 : 3000
         */
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 1);                    // 5000
        orderMenu.put(Main.BARBECUE_RIBS,1);                    // 54000
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);              // 6000
        orderMenu.put(Drink.ZERO_COLA, 1);                      // 3000

        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        EventDto eventDto = new EventDto(1, totalRegularPrice.calculate(orderMenu), orderMenu);
        events.calculateEvent(eventDto);

        /*
         *  총혜택 금액 : -3023
         *
         *  크리스마스 할인    : -1000
         *  주말 할인         : -2023
         */
        int christmasBenefit = -1000;
        int weekendBenefit = -2023;

        Map<String, Integer> benefitDetails = events.getBenefitDetails();
        assertThat(benefitDetails.get("크리스마스 디데이 할인")).isEqualTo(christmasBenefit);
        assertThat(benefitDetails.get("주말 할인")).isEqualTo(weekendBenefit);
        assertThat(benefitDetails.get("평일 할인")).isNull();
        assertThat(benefitDetails.get("특별 할인")).isNull();
        assertThat(benefitDetails.get("증정 이벤트")).isNull();
    }

    @Test
    void 이벤트_개별금액_테스트2() {
        Events events = new Events();

        /*
         *  할인 전 총주문 금액 : 127000
         *
         *  아이스크림 : 10000
         *  바베큐립 : 108000
         *  양송이스프: 6000
         *  제로콜라 : 3000
         */
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);                    // 10000
        orderMenu.put(Main.BARBECUE_RIBS,2);                    // 108000
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);              // 6000
        orderMenu.put(Drink.ZERO_COLA, 1);                      // 3000

        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        EventDto eventDto = new EventDto(24, totalRegularPrice.calculate(orderMenu), orderMenu);
        events.calculateEvent(eventDto);

        /*
         *  총혜택 금액 : -33346
         *
         *  크리스마스 할인 : -3300
         *  평일 할인 : -4046
         *  특별 할인 : -1000
         *  증정 이벤트 : -25000
         */
        int christmasBenefit = -3300;
        int weekdayBenefit = -4046;
        int specialBenefit = -1000;
        int giveawayBenefit = -25000;

        Map<String, Integer> benefitDetails = events.getBenefitDetails();
        assertThat(benefitDetails.get("크리스마스 디데이 할인")).isEqualTo(christmasBenefit);
        assertThat(benefitDetails.get("주말 할인")).isNull();
        assertThat(benefitDetails.get("평일 할인")).isEqualTo(weekdayBenefit);
        assertThat(benefitDetails.get("특별 할인")).isEqualTo(specialBenefit);
        assertThat(benefitDetails.get("증정 이벤트")).isEqualTo(giveawayBenefit);
    }
}