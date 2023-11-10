package christmas.domain.food;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTest {
    @DisplayName("애피타이저 양송이스프,타파스,시저샐러드 정가 테스트")
    @Test
    void 애피타이저_정가_테스트() {
        // 양송이스프
        Food mushroomSoup = Appetizer.MUSHROOM_SOUP;
        int mushroomSoupPrice = 6000;

        Assertions.assertThat(mushroomSoup.calculatePrice()).isEqualTo(mushroomSoupPrice);


        // 타파스
        Food tapas = Appetizer.TAPAS;
        int tapasPrice = 5500;

        Assertions.assertThat(tapas.calculatePrice()).isEqualTo(tapasPrice);


        // 시저샐러드
        Food caesarSalad = Appetizer.CAESAR_SALAD;
        int caesarSaladPrice = 8000;

        Assertions.assertThat(caesarSalad.calculatePrice()).isEqualTo(caesarSaladPrice);
    }

    @DisplayName("메인 티본스테이크,바비큐립,해산물파스타,크리스마스파스타 정가 테스트")
    @Test
    void 메인_정가_테스트() {
        // 티본스테이크
        Food tBoneSteak = Main.T_BONE_STEAK;
        int tBoneSteakPrice = 55000;

        Assertions.assertThat(tBoneSteak.calculatePrice()).isEqualTo(tBoneSteakPrice);

        // 바비큐립
        Food barbecueRibs = Main.BARBECUE_RIBS;
        int barbecueRibsPrice = 54000;

        Assertions.assertThat(barbecueRibs.calculatePrice()).isEqualTo(barbecueRibsPrice);

        
        // 해산물파스타
        Food seafoodPasta = Main.SEAFOOD_PASTA;
        int seafoodPastaPrice = 35000;

        Assertions.assertThat(seafoodPasta.calculatePrice()).isEqualTo(seafoodPastaPrice);


        // 크리스마스파스타
        Food christmasPasta = Main.CHRISTMAS_PASTA;
        int christmasPastaPrice = 25000;

        Assertions.assertThat(christmasPasta.calculatePrice()).isEqualTo(christmasPastaPrice);
    }

    @DisplayName("디저트 초코케이크,아이스크림 정가 테스트")
    @Test
    void 디저트_정가_테스트() {
        // 초코케이크
        Food chocoCake = Dessert.CHOCO_CAKE;
        int chocoCakePrice = 15000;

        Assertions.assertThat(chocoCake.calculatePrice()).isEqualTo(chocoCakePrice);


        // 아이스크림
        Food iceCream = Dessert.ICE_CREAM;
        int iceCreamPrice = 5000;

        Assertions.assertThat(iceCream.calculatePrice()).isEqualTo(iceCreamPrice);
    }

    @DisplayName("음료 제로콜라,레드와인,샴페인 정가 테스트")
    @Test
    void 음료_정가_테스트() {
        // 제로콜라
        Food zeroCola = Drink.ZERO_COLA;
        int zeroColaPrice = 3000;

        Assertions.assertThat(zeroCola.calculatePrice()).isEqualTo(zeroColaPrice);


        // 레드와인
        Food redWine = Drink.RED_WINE;
        int redWinePrice = 60000;

        Assertions.assertThat(redWine.calculatePrice()).isEqualTo(redWinePrice);


        // 샴페인
        Food champagne = Drink.CHAMPAGNE;
        int champagnePrice = 25000;

        Assertions.assertThat(champagne.calculatePrice()).isEqualTo(champagnePrice);
    }
}