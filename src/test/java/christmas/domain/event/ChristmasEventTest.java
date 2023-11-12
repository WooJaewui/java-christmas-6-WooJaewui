package christmas.domain.event;

import christmas.domain.dto.EventDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasEventTest {
    @Test
    void 크리스마스할인_금액_테스트1() {
        ChristmasEvent christmasEvent = new ChristmasEvent();

        int expectedDiscount = -3400;
        EventDto eventDto = new EventDto(25, 0, null);

        assertThat(christmasEvent.calculate(eventDto)).isEqualTo(expectedDiscount);
        assertThat(christmasEvent.getBenefit()).isEqualTo(expectedDiscount);
    }

    @Test
    void 크리스마스할인_금액_테스트2() {
        ChristmasEvent christmasEvent = new ChristmasEvent();

        int expectedDiscount = -1000;
        EventDto eventDto = new EventDto(1, 0, null);

        assertThat(christmasEvent.calculate(eventDto)).isEqualTo(expectedDiscount);
        assertThat(christmasEvent.getBenefit()).isEqualTo(expectedDiscount);
    }

    @Test
    void 크리스마스할인_금액_테스트3() {
        ChristmasEvent christmasEvent = new ChristmasEvent();

        int expectedDiscount = -2600;
        EventDto eventDto = new EventDto(17, 0, null);

        assertThat(christmasEvent.calculate(eventDto)).isEqualTo(expectedDiscount);
        assertThat(christmasEvent.getBenefit()).isEqualTo(expectedDiscount);
    }
}