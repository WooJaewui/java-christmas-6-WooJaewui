package christmas.domain.food;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DessertTest {
    @Test
    void 초코케이크_정가_테스트() {
        Food chocoCake = Dessert.CHOCO_CAKE;
        int chocoCakePrice = 15000;

        Assertions.assertThat(chocoCake.getRegularPrice()).isEqualTo(chocoCakePrice);
    }

    @Test
    void 아이스크림_정가_테스트() {
        Food iceCream = Dessert.ICE_CREAM;
        int iceCreamPrice = 5000;

        Assertions.assertThat(iceCream.getRegularPrice()).isEqualTo(iceCreamPrice);
    }
}