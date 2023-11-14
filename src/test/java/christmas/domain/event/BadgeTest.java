package christmas.domain.event;

import org.junit.jupiter.api.Test;

import static christmas.domain.event.Badge.*;
import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {
    @Test
    void 배지_없음_테스트1() {
        int totalBenefitPrice = 0;

        assertThat(SANTA.calculate(totalBenefitPrice)).isEqualTo(NONE);
    }

    @Test
    void 배지_없음_테스트2() {
        int totalBenefitPrice = 4999;

        assertThat(SANTA.calculate(totalBenefitPrice)).isEqualTo(NONE);
    }

    @Test
    void 배지_별_테스트1() {
        int totalBenefitPrice = 5000;

        assertThat(SANTA.calculate(totalBenefitPrice)).isEqualTo(STAR);
    }

    @Test
    void 배지_별_테스트2() {
        int totalBenefitPrice = 9999;

        assertThat(SANTA.calculate(totalBenefitPrice)).isEqualTo(STAR);
    }

    @Test
    void 배지_트리_테스트1() {
        int totalBenefitPrice = 10000;

        assertThat(NONE.calculate(totalBenefitPrice)).isEqualTo(TREE);
    }

    @Test
    void 배지_트리_테스트2() {
        int totalBenefitPrice = 19999;

        assertThat(NONE.calculate(totalBenefitPrice)).isEqualTo(TREE);
    }

    @Test
    void 배지_산타_테스트1() {
        int totalBenefitPrice = 20000;

        assertThat(STAR.calculate(totalBenefitPrice)).isEqualTo(SANTA);
    }

    @Test
    void 배지_산타_테스트2() {
        int totalBenefitPrice = 5783484;

        assertThat(STAR.calculate(totalBenefitPrice)).isEqualTo(SANTA);
    }
}