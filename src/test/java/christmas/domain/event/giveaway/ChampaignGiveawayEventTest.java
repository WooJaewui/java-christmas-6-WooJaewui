package christmas.domain.event.giveaway;

import christmas.domain.dto.EventDto;
import christmas.domain.event.giveaway.ChampaignGiveawayEvent;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChampaignGiveawayEventTest {
    @Test
    void 증정할인_개수_가격_테스트1() {
        ChampaignGiveawayEvent giveawayEvent = new ChampaignGiveawayEvent();

        int expectedCount = 1;
        int expectedPrice = -25000;
        EventDto eventDto = new EventDto(1, 120000, null);

        assertThat(giveawayEvent.isEvent(eventDto)).isTrue();
        assertThat(giveawayEvent.calculate(eventDto)).isEqualTo(expectedCount);
        assertThat(giveawayEvent.getCount()).isEqualTo(expectedCount);
        assertThat(giveawayEvent.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 증정할인_개수_가격_테스트2() {
        ChampaignGiveawayEvent giveawayEvent = new ChampaignGiveawayEvent();

        int expectedCount = 0;
        int expectedPrice = 0;
        EventDto eventDto = new EventDto(1, 110000, null);

        assertThat(giveawayEvent.isEvent(eventDto)).isFalse();
        assertThat(giveawayEvent.calculate(eventDto)).isEqualTo(expectedCount);
        assertThat(giveawayEvent.getCount()).isEqualTo(expectedCount);
        assertThat(giveawayEvent.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 증정할인_개수_가격_테스트3() {
        ChampaignGiveawayEvent giveawayEvent = new ChampaignGiveawayEvent();

        int expectedCount = 5;
        int expectedPrice = -125000;
        EventDto eventDto = new EventDto(1, 700000, null);

        assertThat(giveawayEvent.isEvent(eventDto)).isTrue();
        assertThat(giveawayEvent.calculate(eventDto)).isEqualTo(expectedCount);
        assertThat(giveawayEvent.getCount()).isEqualTo(expectedCount);
        assertThat(giveawayEvent.getBenefit()).isEqualTo(expectedPrice);
    }
}