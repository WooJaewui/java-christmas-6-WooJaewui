package christmas.domain.price;

import christmas.domain.dto.EventDto;
import christmas.domain.food.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TotalRegularPriceTest {
    @Test
    void 할인전총금액_테스트() {
        TotalRegularPrice totalRegularPrice = new TotalRegularPrice();
        Map<Food, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Dessert.ICE_CREAM, 2);
        orderMenu.put(Main.BARBECUE_RIBS,2);
        orderMenu.put(Appetizer.MUSHROOM_SOUP, 1);
        orderMenu.put(Drink.RED_WINE, 1);

        totalRegularPrice.calculate(orderMenu);
        int expectedTotalBenefitPrice = 184000;

        assertThat(totalRegularPrice.get()).isEqualTo(expectedTotalBenefitPrice);
    }
}