package christmas.domain.event;

import christmas.domain.dto.EventDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialEventTest {
    @ParameterizedTest
    @ValueSource(ints = {1,2,4,5,6,7,8,9,11,12,13,14,15,16,18,19,20,21,22,23,26,27,28,29,30})
    void 특별할인_금액_테스트1(int reservationDate) {
        SpecialEvent specialDiscount = new SpecialEvent();

        int expectedPrice = 0;
        EventDto eventDto = new EventDto(reservationDate, 0, null);

        assertThat(specialDiscount.isEvent(eventDto)).isFalse();
        assertThat(specialDiscount.calculate(eventDto)).isEqualTo(expectedPrice);
        assertThat(specialDiscount.getBenefit()).isEqualTo(expectedPrice);
    }

    @ParameterizedTest
    @ValueSource(ints = {3,10,17,24,25,31})
    void 특별할인_금액_테스트2(int reservationDate) {
        SpecialEvent specialDiscount = new SpecialEvent();

        int expectedPrice = -1000;
        EventDto eventDto = new EventDto(reservationDate, 0, null);

        assertThat(specialDiscount.isEvent(eventDto)).isTrue();
        assertThat(specialDiscount.calculate(eventDto)).isEqualTo(expectedPrice);
        assertThat(specialDiscount.getBenefit()).isEqualTo(expectedPrice);
    }
}