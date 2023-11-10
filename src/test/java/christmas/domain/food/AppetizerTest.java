package christmas.domain.food;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AppetizerTest {
    @Test
    void 양송이스프_정가_테스트() {
        Food mushroomSoup = Appetizer.MUSHROOM_SOUP;
        int mushroomSoupPrice = 6000;

        assertThat(mushroomSoup.getRegularPrice()).isEqualTo(mushroomSoupPrice);
    }

    @Test
    void 양송이스프_크리스마스할인_테스트() {
        Food mushroomSoup = Appetizer.MUSHROOM_SOUP;
        int mushroomSoupPrice = 6000;

        assertThat(mushroomSoup.getRegularPrice()).isEqualTo(mushroomSoupPrice);
    }

    @Test
    void 타파스_정가_테스트() {
        Food tapas = Appetizer.TAPAS;
        int tapasPrice = 5500;

        assertThat(tapas.getRegularPrice()).isEqualTo(tapasPrice);
    }

    @Test
    void 시저샐러드_정가_테스트() {
        Food caesarSalad = Appetizer.CAESAR_SALAD;
        int caesarSaladPrice = 8000;

        assertThat(caesarSalad.getRegularPrice()).isEqualTo(caesarSaladPrice);
    }
}