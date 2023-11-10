package christmas.domain;

import christmas.domain.food.Appetizer;
import christmas.domain.food.Dessert;
import christmas.domain.food.Food;
import christmas.domain.food.Main;
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
}