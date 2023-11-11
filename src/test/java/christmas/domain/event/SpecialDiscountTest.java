package christmas.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountTest {
    @ParameterizedTest
    @ValueSource(ints = {1,2,4,5,6,7,8,9,11,12,13,14,15,16,18,19,20,21,22,23,26,27,28,29,30})
    void 특별할인_금액_테스트1(int reservationDate) {
        SpecialDiscount specialDiscount = new SpecialDiscount();
        int expectedPrice = 0;

        Assertions.assertThat(specialDiscount.calculate(reservationDate)).isEqualTo(expectedPrice);
        Assertions.assertThat(specialDiscount.get()).isEqualTo(expectedPrice);
    }

    @ParameterizedTest
    @ValueSource(ints = {3,10,17,24,25,31})
    void 특별할인_금액_테스트2(int reservationDate) {
        SpecialDiscount specialDiscount = new SpecialDiscount();
        int expectedPrice = 1000;

        Assertions.assertThat(specialDiscount.calculate(reservationDate)).isEqualTo(expectedPrice);
        Assertions.assertThat(specialDiscount.get()).isEqualTo(expectedPrice);
    }
}