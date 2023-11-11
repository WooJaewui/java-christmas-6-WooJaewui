package christmas.domain.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasEventTest {
    @Test
    void 크리스마스할인_금액_테스트1() {
        ChristmasEvent christmasEvent = new ChristmasEvent();

        int expectedDiscount = -3400;
        assertThat(christmasEvent.calculate(25)).isEqualTo(expectedDiscount);
        assertThat(christmasEvent.getBenefit()).isEqualTo(expectedDiscount);
    }

    @Test
    void 크리스마스할인_금액_테스트2() {
        ChristmasEvent christmasEvent = new ChristmasEvent();

        int expectedDiscount = -1000;
        assertThat(christmasEvent.calculate(1)).isEqualTo(expectedDiscount);
        assertThat(christmasEvent.getBenefit()).isEqualTo(expectedDiscount);
    }

    @Test
    void 크리스마스할인_금액_테스트3() {
        ChristmasEvent christmasEvent = new ChristmasEvent();

        int expectedDiscount = -2600;
        assertThat(christmasEvent.calculate(17)).isEqualTo(expectedDiscount);
        assertThat(christmasEvent.getBenefit()).isEqualTo(expectedDiscount);
    }
}