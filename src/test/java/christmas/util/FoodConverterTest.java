package christmas.util;

import christmas.domain.food.Food;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static christmas.domain.food.Appetizer.*;
import static christmas.domain.food.Dessert.CHOCO_CAKE;
import static christmas.domain.food.Dessert.ICE_CREAM;
import static christmas.domain.food.Drink.*;
import static christmas.domain.food.Main.*;
import static christmas.util.FoodConverter.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FoodConverterTest {
    @Test
    void 주문메뉴_맵_변경_테스트1() {
        String input = "양송이수프-2,아이스크림-5";

        Map<Food, Integer> orderMenu = convertStringToOrderMenus(input);
        assertThat(orderMenu.get(MUSHROOM_SOUP)).isEqualTo(2);
        assertThat(orderMenu.get(ICE_CREAM)).isEqualTo(5);
    }

    @Test
    void 주문메뉴_맵_변경_테스트2() {
        String input = "타파스-4,시저샐러드-7,티본스테이크-2,바비큐립-6,제로콜라-2";

        Map<Food, Integer> orderMenu = convertStringToOrderMenus(input);
        assertThat(orderMenu.get(TAPAS)).isEqualTo(4);
        assertThat(orderMenu.get(CAESAR_SALAD)).isEqualTo(7);
        assertThat(orderMenu.get(T_BONE_STEAK)).isEqualTo(2);
        assertThat(orderMenu.get(BARBECUE_RIBS)).isEqualTo(6);
        assertThat(orderMenu.get(ZERO_COLA)).isEqualTo(2);
    }

    @Test
    void 주문메뉴_맵_변경_테스트3() {
        String input = "레드와인-3,해산물파스타-1,초코케이크-3,크리스마스파스타-1,바비큐립-6";

        Map<Food, Integer> orderMenu = convertStringToOrderMenus(input);
        assertThat(orderMenu.get(RED_WINE)).isEqualTo(3);
        assertThat(orderMenu.get(SEAFOOD_PASTA)).isEqualTo(1);
        assertThat(orderMenu.get(CHOCO_CAKE)).isEqualTo(3);
        assertThat(orderMenu.get(CHRISTMAS_PASTA)).isEqualTo(1);
        assertThat(orderMenu.get(BARBECUE_RIBS)).isEqualTo(6);
    }

    @Test
    void 주문메뉴_중복주문_오류테스트() {
        String input = "피자-2,아이스크림-1,피자-1";

        assertThatThrownBy(() -> convertStringToOrderMenus(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문메뉴_메뉴개수_변경_테스트() {
        String input = "피자-2,아이스크림-1,피자-1";

        List<String> list = convertStringToMenuCounts(input);
        assertThat(list.get(0)).isEqualTo("피자-2");
        assertThat(list.get(1)).isEqualTo("아이스크림-1");
        assertThat(list.get(2)).isEqualTo("피자-1");
    }

    @Test
    void 주문메뉴_음식_변경_테스트() {
        String appetizer1 = "양송이수프";
        String appetizer2 = "타파스";
        String appetizer3 = "시저샐러드";

        String main1 = "티본스테이크";
        String main2 = "바비큐립";
        String main3 = "해산물파스타";
        String main4 = "크리스마스파스타";
        
        String desert1 = "초코케이크";
        String desert2 = "아이스크림";

        String drink1 = "제로콜라";
        String drink2 = "레드와인";
        String drink3 = "샴페인";

        assertThat(convertStringToFood(appetizer1)).isEqualTo(MUSHROOM_SOUP);
        assertThat(convertStringToFood(appetizer2)).isEqualTo(TAPAS);
        assertThat(convertStringToFood(appetizer3)).isEqualTo(CAESAR_SALAD);

        assertThat(convertStringToFood(main1)).isEqualTo(T_BONE_STEAK);
        assertThat(convertStringToFood(main2)).isEqualTo(BARBECUE_RIBS);
        assertThat(convertStringToFood(main3)).isEqualTo(SEAFOOD_PASTA);
        assertThat(convertStringToFood(main4)).isEqualTo(CHRISTMAS_PASTA);

        assertThat(convertStringToFood(desert1)).isEqualTo(CHOCO_CAKE);
        assertThat(convertStringToFood(desert2)).isEqualTo(ICE_CREAM);

        assertThat(convertStringToFood(drink1)).isEqualTo(ZERO_COLA);
        assertThat(convertStringToFood(drink2)).isEqualTo(RED_WINE);
        assertThat(convertStringToFood(drink3)).isEqualTo(CHAMPAGNE);
    }

    @Test
    void 주문메뉴_음식_변경_오류테스트() {
        String input1 = "피자";
        String input2 = "치킨";
        String input3 = "양송이스프";
        String input4 = "해산물 파스타";

        assertThatThrownBy(() -> convertStringToFood(input1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> convertStringToFood(input2)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> convertStringToFood(input3)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> convertStringToFood(input4)).isInstanceOf(IllegalArgumentException.class);
    }
}