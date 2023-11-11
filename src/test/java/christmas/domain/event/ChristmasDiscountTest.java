package christmas.domain.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountTest {
    @Test
    void 크리스마스할인_금액_테스트1() {
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();

        int expectedDiscount = -3400;
        assertThat(christmasDiscount.calculate(25)).isEqualTo(expectedDiscount);
        assertThat(christmasDiscount.get()).isEqualTo(expectedDiscount);
    }

    @Test
    void 크리스마스할인_금액_테스트2() {
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();

        int expectedDiscount = -1000;
        assertThat(christmasDiscount.calculate(1)).isEqualTo(expectedDiscount);
        assertThat(christmasDiscount.get()).isEqualTo(expectedDiscount);
    }

    @Test
    void 크리스마스할인_금액_테스트3() {
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();

        int expectedDiscount = -2600;
        assertThat(christmasDiscount.calculate(17)).isEqualTo(expectedDiscount);
        assertThat(christmasDiscount.get()).isEqualTo(expectedDiscount);
    }
}