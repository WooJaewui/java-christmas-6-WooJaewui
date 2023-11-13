package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.food.Food;
import christmas.util.Converter;

import java.util.List;
import java.util.Map;

public class InputView {
    public static int readReservationDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();

        checkReservationDate(input);

        return Integer.parseInt(input);
    }

    private static void checkReservationDate(String input) {
        InputValidation.checkReservationDateConvertNumber(input);
        InputValidation.checkReservationDateRange(input);
    }

    public static Map<Food, Integer> readOrderMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();

        checkOrderMenu(input);

        return Converter.convertStringToOrderMenus(input);
    }

    private static void checkOrderMenu(String input) {
        List<String> menuCounts = Converter.convertStringToMenuCounts(input);
        InputValidation.checkOrderMenuInMyMenu(menuCounts);
        InputValidation.checkOrderMenuCountConvertNumber(menuCounts);
        InputValidation.checkOrderMenuCountMinRange(menuCounts);
        InputValidation.checkOrderMenuCountMaxRange(menuCounts);
    }

    private static class InputValidation {
        private static boolean isNotInteger(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private static void checkReservationDateConvertNumber(String input) {
            if (isNotInteger(input)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }

        private static void checkReservationDateRange(String input) {
            int reservationDate = Integer.parseInt(input);
            if (1 > reservationDate || reservationDate > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }

        private static void checkOrderMenuInMyMenu(List<String> orderMenus) {
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split("-");
                Converter.convertStringToFood(menuAndCount[0]);
            }
        }

        private static void checkOrderMenuCountConvertNumber(List<String> orderMenus) {
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split("-");
                if (isNotInteger(menuAndCount[1])) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
            }
        }

        private static void checkOrderMenuCountMinRange(List<String> orderMenus) {
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split("-");
                int menuCount = Integer.parseInt(menuAndCount[1]);
                if (menuCount < 1) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
            }
        }

        private static void checkOrderMenuCountMaxRange(List<String> orderMenus) {
            int menuCount = 0;
            for (String orderMenu : orderMenus) {
                String[] menuAndCount = orderMenu.split("-");
                menuCount += Integer.parseInt(menuAndCount[1]);
            }

            if (menuCount > 20) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }
}
