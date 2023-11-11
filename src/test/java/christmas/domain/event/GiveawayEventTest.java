package christmas.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GiveawayEventTest {
    @Test
    void 증정할인_개수_가격_테스트1() {
        GiveawayEvent giveawayEvent = new GiveawayEvent();
        if (giveawayEvent.isEvent(120000)) {
            giveawayEvent.calculate(120000);
        }

        int expectedCount = 1;
        int expectedPrice = -25000;
        Assertions.assertThat(giveawayEvent.getCount()).isEqualTo(expectedCount);
        Assertions.assertThat(giveawayEvent.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 증정할인_개수_가격_테스트2() {
        GiveawayEvent giveawayEvent = new GiveawayEvent();
        if (giveawayEvent.isEvent(110000)) {
            giveawayEvent.calculate(110000);
        }

        int expectedCount = 0;
        int expectedPrice = 0;
        Assertions.assertThat(giveawayEvent.getCount()).isEqualTo(expectedCount);
        Assertions.assertThat(giveawayEvent.getBenefit()).isEqualTo(expectedPrice);
    }

    @Test
    void 증정할인_개수_가격_테스트3() {
        GiveawayEvent giveawayEvent = new GiveawayEvent();
        if (giveawayEvent.isEvent(700000)) {
            giveawayEvent.calculate(700000);
        }

        int expectedCount = 5;
        int expectedPrice = -125000;
        Assertions.assertThat(giveawayEvent.getCount()).isEqualTo(expectedCount);
        Assertions.assertThat(giveawayEvent.getBenefit()).isEqualTo(expectedPrice);
    }
}