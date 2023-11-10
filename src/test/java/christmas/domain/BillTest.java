package christmas.domain;

import christmas.domain.food.Appetizer;
import christmas.domain.food.Food;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {
    @Test
    void 총금액_테스트() {
        Bill bill = new Bill(25);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Appetizer.CAESAR_SALAD);
        orderMenu.add(Appetizer.CAESAR_SALAD);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);

        bill.inputOrderMenu(orderMenu);

        int expectedTotalPrice = 22000;

        assertThat(bill.getTotalRegularPrice()).isEqualTo(expectedTotalPrice);
    }
}