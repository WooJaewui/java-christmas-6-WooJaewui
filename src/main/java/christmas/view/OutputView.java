package christmas.view;

public class OutputView {
    public static void printEventPreview() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public static void printMenu(String message) {
        System.out.println("<주문 메뉴>");
        System.out.println(message);
    }

    public static void printTotalRegularPrice(String message) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(message);
    }

    public static void printGiveawayEvent(String message) {
        System.out.println("<증정 메뉴>");
        System.out.println(message);
    }

    public static void printBenefitList(String message) {
        System.out.println("<혜택 내역>");
        System.out.println(message);
    }

    public static void printBenefitPrice(String message) {
        System.out.println("<총혜택 금액>");
        System.out.println(message);
    }

    public static void printPaymentPrice(String message) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(message);
    }

    public static void printEventBadge(String message) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(message);
    }
}
