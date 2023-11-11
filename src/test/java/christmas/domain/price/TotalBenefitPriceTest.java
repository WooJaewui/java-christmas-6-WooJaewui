package christmas.domain.price;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TotalBenefitPriceTest {
    @Test
    void 혜택_총금액_테스트() {
        TotalBenefitPrice totalBenefitPrice = new TotalBenefitPrice();
        totalBenefitPrice.calculate(40000);

        int expectedTotalBenefitPrice = 40000;
        assertThat(totalBenefitPrice.get()).isEqualTo(expectedTotalBenefitPrice);
    }
}