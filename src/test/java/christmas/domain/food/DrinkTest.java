package christmas.domain.food;

import christmas.domain.Bill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DrinkTest {
    @Test
    void 제로콜라_정가_테스트() {
        Food zeroCola = Drink.ZERO_COLA;
        int zeroColaPrice = 3000;

        Assertions.assertThat(zeroCola.getRegularPrice()).isEqualTo(zeroColaPrice);
    }

    @Test
    void 레드와인_정가_테스트() {
        Food redWine = Drink.RED_WINE;
        int redWinePrice = 60000;

        Assertions.assertThat(redWine.getRegularPrice()).isEqualTo(redWinePrice);
    }

    @Test
    void 샴페인_정가_테스트() {
        Food champagne = Drink.CHAMPAGNE;
        int champagnePrice = 25000;

        Assertions.assertThat(champagne.getRegularPrice()).isEqualTo(champagnePrice);
    }
}