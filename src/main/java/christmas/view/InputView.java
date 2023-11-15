package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.food.Drink;
import christmas.domain.food.Food;
import christmas.util.FoodConverter;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InputView {
    private static final String READ_RESERVATION_DATE_MESSAGE
            = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_ORDER_MENU_MESSAGE
            = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int readReservationDate() {
        System.out.println(READ_RESERVATION_DATE_MESSAGE);
        String input = Console.readLine();

        checkReservationDate(input);

        return Integer.parseInt(input);
    }

    private static void checkReservationDate(String input) {
        InputValidation.checkReservationDateConvertNumber(input);
        InputValidation.checkReservationDateRange(input);
    }

    public static Map<Food, Integer> readOrderMenu() {
        System.out.println(READ_ORDER_MENU_MESSAGE);
        String input = Console.readLine();

        checkOrderMenu(input);

        return FoodConverter.convertStringToOrderMenus(input);
    }

    private static void checkOrderMenu(String input) {
        InputValidation.checkDoubleComma(input);
        List<String> menuCounts = FoodConverter.convertStringToMenuCounts(input);
        try {
            InputValidation.checkOrderMenuInMyMenu(menuCounts);
            InputValidation.checkOrderMenuCountConvertNumber(menuCounts);
            InputValidation.checkOrderMenuCountMinRange(menuCounts);
            InputValidation.checkOrderMenuCountMaxRange(menuCounts);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(InputValidation.ORDER_MENU_EXCEPTION, e);
        }

        InputValidation.checkAllMenuDrink(input);
    }

    private static class InputValidation {
        private static final String RESERVATION_DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
        private static final String ORDER_MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
        private static final String HYPHEN = "-";
        private static final int MIN_DATE = 1;
        private static final int MAX_DATE = 31;
        private static final String DOUBLE_COMMA = ",,";
        private static final int MIN_COUNT = 1;
        private static final int MAX_COUNT = 20;

        private static boolean isNotInteger(String input) {
            try {
                Integer.parseInt(input);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }

        private static void checkReservationDateConvertNumber(String input) {
            if (isNotInteger(input)) {
                throw new IllegalArgumentException(RESERVATION_DATE_EXCEPTION);
            }
        }

        private static void checkReservationDateRange(String input) {
            int reservationDate = Integer.parseInt(input);
            if (MIN_DATE > reservationDate || reservationDate > MAX_DATE) {
                throw new IllegalArgumentException(RESERVATION_DATE_EXCEPTION);
            }
        }

        private static void checkDoubleComma(String input) {
            if (input.contains(DOUBLE_COMMA)) {
                throw new IllegalArgumentException(ORDER_MENU_EXCEPTION);
            }
        }

        private static void checkOrderMenuInMyMenu(List<String> orderMenus) {
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split(HYPHEN);
                FoodConverter.convertStringToFood(menuAndCount[0]);
            }
        }

        private static void checkOrderMenuCountConvertNumber(List<String> orderMenus) {
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split(HYPHEN);
                if (isNotInteger(menuAndCount[1])) {
                    throw new IllegalArgumentException(ORDER_MENU_EXCEPTION);
                }
            }
        }

        private static void checkOrderMenuCountMinRange(List<String> orderMenus) {
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split(HYPHEN);
                int menuCount = Integer.parseInt(menuAndCount[1]);
                if (menuCount < MIN_COUNT) {
                    throw new IllegalArgumentException(ORDER_MENU_EXCEPTION);
                }
            }
        }

        private static void checkOrderMenuCountMaxRange(List<String> orderMenus) {
            int menuCount = 0;
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split(HYPHEN);
                menuCount += Integer.parseInt(menuAndCount[1]);
            }

            if (menuCount > MAX_COUNT) {
                throw new IllegalArgumentException(ORDER_MENU_EXCEPTION);
            }
        }

        private static void checkAllMenuDrink(String input) {
            Map<Food, Integer> orderMenus = FoodConverter.convertStringToOrderMenus(input);
            Set<Food> foods = orderMenus.keySet();
            if (foods.stream().allMatch(food -> food instanceof Drink)) {
                throw new IllegalArgumentException(ORDER_MENU_EXCEPTION);
            }
        }
    }
}
