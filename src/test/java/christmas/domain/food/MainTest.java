package christmas.domain.food;

import christmas.domain.Bill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {
    @Test
    void 티본스테이크_정가_테스트() {
        Food tBoneSteak = Main.T_BONE_STEAK;
        int tBoneSteakPrice = 55000;

        Assertions.assertThat(tBoneSteak.getRegularPrice()).isEqualTo(tBoneSteakPrice);
    }

    @Test
    void 바베큐립_정가_테스트() {
        Food barbecueRibs = Main.BARBECUE_RIBS;
        int barbecueRibsPrice = 54000;

        Assertions.assertThat(barbecueRibs.getRegularPrice()).isEqualTo(barbecueRibsPrice);
    }

    @Test
    void 해산물파스타_정가_테스트() {
        Food seafoodPasta = Main.SEAFOOD_PASTA;
        int seafoodPastaPrice = 35000;

        Assertions.assertThat(seafoodPasta.getRegularPrice()).isEqualTo(seafoodPastaPrice);
    }

    @Test
    void 크리스마스파스타_정가_테스트() {
        Food christmasPasta = Main.CHRISTMAS_PASTA;
        int christmasPastaPrice = 25000;

        Assertions.assertThat(christmasPasta.getRegularPrice()).isEqualTo(christmasPastaPrice);
    }
}