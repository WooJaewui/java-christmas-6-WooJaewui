package christmas.util;

import christmas.domain.food.Food;

import java.util.*;

import static christmas.domain.food.Appetizer.*;
import static christmas.domain.food.Dessert.CHOCO_CAKE;
import static christmas.domain.food.Dessert.ICE_CREAM;
import static christmas.domain.food.Drink.*;
import static christmas.domain.food.Main.*;
import static christmas.domain.food.Main.CHRISTMAS_PASTA;

public class Converter {
    private static final List<Food> foods = Arrays.asList(
            MUSHROOM_SOUP, TAPAS, CAESAR_SALAD,
            T_BONE_STEAK, BARBECUE_RIBS, SEAFOOD_PASTA, CHRISTMAS_PASTA,
            CHOCO_CAKE, ICE_CREAM,
            ZERO_COLA, RED_WINE, CHAMPAGNE
    );

    public static Map<Food, Integer> convertStringToOrderMenus(String input) {
        Map<Food, Integer> orderMenus = new HashMap<>();
        List<String> menuAndCounts = convertStringToMenuCounts(input);
        for (String menuAndCount : menuAndCounts) {
            String[] menuCount = menuAndCount.split("-");
            Food food = convertStringToFood(menuCount[0]);
            int count = Integer.parseInt(menuCount[1]);
            if (orderMenus.get(food) != null) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            orderMenus.put(food, count);
        }

        return orderMenus;
    }

    public static List<String> convertStringToMenuCounts(String input) {
        return new ArrayList<>(Arrays.stream(input.split(",")).toList());
    }

    public static Food convertStringToFood(String input) {
        Optional<Food> orderFood = foods.stream().filter(food -> food.getName().equals(input)).findFirst();
        if (orderFood.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        return orderFood.get();
    }
}
