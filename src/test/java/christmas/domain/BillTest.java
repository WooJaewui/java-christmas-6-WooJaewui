package christmas.domain;

import christmas.domain.food.Appetizer;
import christmas.domain.food.Dessert;
import christmas.domain.food.Food;
import christmas.domain.food.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    void 평일할인_테스트1() {
        Bill bill = new Bill(3);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Appetizer.CAESAR_SALAD);
        orderMenu.add(Appetizer.CAESAR_SALAD);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);
        orderMenu.add(Dessert.CHOCO_CAKE);
        orderMenu.add(Dessert.ICE_CREAM);

        bill.inputOrderMenu(orderMenu);
        bill.calculateWeekdayDiscount();

        int expectedWeekdayDiscount = 4046;
        assertThat(bill.getWeekdayDiscount()).isEqualTo(expectedWeekdayDiscount);
    }

    @Test
    void 평일할인_테스트2() {
        Bill bill = new Bill(3);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Main.BARBECUE_RIBS);
        orderMenu.add(Main.SEAFOOD_PASTA);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);
        orderMenu.add(Dessert.CHOCO_CAKE);
        orderMenu.add(Dessert.ICE_CREAM);

        bill.inputOrderMenu(orderMenu);
        bill.calculateWeekdayDiscount();

        int expectedWeekdayDiscount = 4046;
        assertThat(bill.getWeekdayDiscount()).isEqualTo(expectedWeekdayDiscount);
    }

    @Test
    void 평일할인_테스트3() {
        Bill bill = new Bill(1);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Main.BARBECUE_RIBS);
        orderMenu.add(Main.SEAFOOD_PASTA);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);
        orderMenu.add(Dessert.CHOCO_CAKE);
        orderMenu.add(Dessert.ICE_CREAM);

        bill.inputOrderMenu(orderMenu);
        bill.calculateWeekdayDiscount();

        int expectedWeekdayDiscount = 0;
        assertThat(bill.getWeekdayDiscount()).isEqualTo(expectedWeekdayDiscount);
    }

    @Test
    void 주말할인_테스트1() {
        Bill bill = new Bill(1);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Appetizer.CAESAR_SALAD);
        orderMenu.add(Appetizer.CAESAR_SALAD);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);
        orderMenu.add(Dessert.CHOCO_CAKE);
        orderMenu.add(Dessert.ICE_CREAM);

        bill.inputOrderMenu(orderMenu);
        bill.calculateWeekendDiscount();

        int expectedWeekendDiscount = 0;
        assertThat(bill.getWeekendDiscount()).isEqualTo(expectedWeekendDiscount);
    }

    @Test
    void 주말할인_테스트2() {
        Bill bill = new Bill(30);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Main.BARBECUE_RIBS);
        orderMenu.add(Main.SEAFOOD_PASTA);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);
        orderMenu.add(Dessert.CHOCO_CAKE);
        orderMenu.add(Dessert.ICE_CREAM);

        bill.inputOrderMenu(orderMenu);
        bill.calculateWeekendDiscount();

        int expectedWeekendDiscount = 4046;
        assertThat(bill.getWeekendDiscount()).isEqualTo(expectedWeekendDiscount);
    }

    @Test
    void 주말할인_테스트3() {
        Bill bill = new Bill(4);
        List<Food> orderMenu = new ArrayList<>();
        orderMenu.add(Main.BARBECUE_RIBS);
        orderMenu.add(Main.SEAFOOD_PASTA);
        orderMenu.add(Appetizer.MUSHROOM_SOUP);
        orderMenu.add(Dessert.CHOCO_CAKE);
        orderMenu.add(Dessert.ICE_CREAM);

        bill.inputOrderMenu(orderMenu);
        bill.calculateWeekendDiscount();

        int expectedWeekendDiscount = 0;
        assertThat(bill.getWeekendDiscount()).isEqualTo(expectedWeekendDiscount);
    }

    @ParameterizedTest
    @ValueSource(ints = {3,10,17,24,25,31})
    void 특별할인_테스트1(int specialDay) {
        Bill bill = new Bill(specialDay);
        int expected = 1000;

        assertThat(bill.getSpecialDiscount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,4,5,6,7,8,9,11,12,13,14,15,16,18,19,20,21,22,23,26,27,28,29,30})
    void 특별할인_테스트2(int specialDay) {
        Bill bill = new Bill(specialDay);
        int expected = 0;

        assertThat(bill.getSpecialDiscount()).isEqualTo(expected);
    }
}