package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {
    @Test
    void 크리스마스_할인금액_테스트() {
        Bill bill = new Bill(25);
        int expected = 3400;

        assertThat(bill.getChristmasDiscount()).isEqualTo(expected);
    }
}