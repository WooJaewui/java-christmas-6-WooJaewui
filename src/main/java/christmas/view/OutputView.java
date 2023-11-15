package christmas.view;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EVENT_PREVIEW_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_REGULAR_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIVEAWAY_MESSAGE = "<증정 메뉴>";
    private static final String BENEFITS_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_MESSAGE = "<총혜택 금액>";
    private static final String PAYMENT_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_MESSAGE = "<12월 이벤트 배지>";

    public static void printEventPreview() {
        System.out.println(EVENT_PREVIEW_MESSAGE + LINE_SEPARATOR);
    }

    public static void printMenus(String message) {
        System.out.println(ORDER_MENU_MESSAGE);
        System.out.println(message);
    }

    public static void printTotalRegularPrice(String message) {
        System.out.println(TOTAL_REGULAR_PRICE_MESSAGE);
        System.out.println(message);
    }

    public static void printGiveawayEvent(String message) {
        System.out.println(GIVEAWAY_MESSAGE);
        System.out.println(message);
    }

    public static void printBenefits(String message) {
        System.out.println(BENEFITS_MESSAGE);
        System.out.println(message);
    }

    public static void printBenefitPrice(String message) {
        System.out.println(TOTAL_BENEFIT_PRICE_MESSAGE);
        System.out.println(message);
    }

    public static void printPaymentPrice(String message) {
        System.out.println(PAYMENT_PRICE_MESSAGE);
        System.out.println(message);
    }

    public static void printEventBadge(String message) {
        System.out.println(BADGE_MESSAGE);
        System.out.println(message);
    }
}
