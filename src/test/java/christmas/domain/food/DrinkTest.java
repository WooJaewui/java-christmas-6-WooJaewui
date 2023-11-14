package christmas.domain.food;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DrinkTest {
    @Test
    void 제로콜라_정가_테스트() {
        Food zeroCola = Drink.ZERO_COLA;
        int zeroColaPrice = 3000;

        assertThat(zeroCola.getRegularPrice()).isEqualTo(zeroColaPrice);
    }

    @Test
    void 레드와인_정가_테스트() {
        Food redWine = Drink.RED_WINE;
        int redWinePrice = 60000;

        assertThat(redWine.getRegularPrice()).isEqualTo(redWinePrice);
    }

    @Test
    void 샴페인_정가_테스트() {
        Food champagne = Drink.CHAMPAGNE;
        int champagnePrice = 25000;

        assertThat(champagne.getRegularPrice()).isEqualTo(champagnePrice);
    }
}