package christmas.util;

import christmas.domain.dto.EventDto;
import christmas.domain.event.Badge;
import christmas.domain.event.ChampaignGiveawayEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.category.Event;
import christmas.domain.event.category.GiveawayEvent;
import christmas.domain.food.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.event.Badge.*;
import static christmas.domain.food.Appetizer.*;
import static christmas.util.MessageConverter.*;
import static org.assertj.core.api.Assertions.*;

class MessageConverterTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 주문메뉴_리스트_메세지변경_테스트() {
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(MUSHROOM_SOUP, 2);
        orderMenu.put(CAESAR_SALAD, 1);

        String[] expected = {"시저샐러드 1개", "양송이수프 2개"};
        String[] messages = convertMenuAndCountsMessage(orderMenu).split(LINE_SEPARATOR);

        assertThat(messages).contains(expected[0]);
        assertThat(messages).contains(expected[1]);
    }

    @Test
    void 금액_메시지변경_테스트1() {
        int price = 200000;

        String expected = "200,000원" + LINE_SEPARATOR;
        assertThat(convertPriceMessage(price)).isEqualTo(expected);
    }

    @Test
    void 금액_메시지변경_테스트2() {
        int price = -123123;

        String expected = "-123,123원" + LINE_SEPARATOR;
        assertThat(convertPriceMessage(price)).isEqualTo(expected);
    }

    @Test
    void 증정메뉴_메시지변경_테스트() {
        ChampaignGiveawayEvent champaignGiveawayEvent = new ChampaignGiveawayEvent();
        champaignGiveawayEvent.calculate(new EventDto(1,120000, null));

        List<GiveawayEvent> giveawayEvents = List.of(champaignGiveawayEvent);

        assertThat(convertGiveawaysMessage(giveawayEvents)).isEqualTo("샴페인 1개" + LINE_SEPARATOR);
    }

    @Test
    void 총혜택금액_메시지변경_테스트1() {
        List<Event> events = List.of();

        assertThat(convertBenefits(events)).isEqualTo("없음" + LINE_SEPARATOR);
    }

    @Test
    void 총혜택금액_메시지변경_테스트2() {
        EventDto eventDto = new EventDto(3, 120000, null);
        ChampaignGiveawayEvent champaignGiveawayEvent = new ChampaignGiveawayEvent();
        champaignGiveawayEvent.calculate(eventDto);
        SpecialEvent specialEvent = new SpecialEvent();
        specialEvent.calculate(eventDto);

        List<Event> events = Arrays.asList(
                champaignGiveawayEvent,
                specialEvent
        );

        String[] messages = convertBenefits(events).split(LINE_SEPARATOR);
        String[] expected = { "증정 이벤트: -25,000원", "특별 할인: -1,000원"};

        assertThat(messages).contains(expected[0]);
        assertThat(messages).contains(expected[1]);
    }

    @Test
    void 배지_메시지변경_테스트() {
        assertThat(convertBadgeName(NONE)).isEqualTo("없음");
        assertThat(convertBadgeName(STAR)).isEqualTo("별");
        assertThat(convertBadgeName(TREE)).isEqualTo("트리");
        assertThat(convertBadgeName(SANTA)).isEqualTo("산타");
    }
}