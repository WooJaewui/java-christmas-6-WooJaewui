package christmas.util;

import christmas.domain.event.Badge;
import christmas.domain.event.Event;
import christmas.domain.event.giveaway.GiveawayEvent;
import christmas.domain.food.Food;

import java.util.List;
import java.util.Map;

public class MessageConverter {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String BLANK = " ";
    private static final char HYPHEN = '-';
    private static final String COMMA = ",";
    private static final String COLON = ": ";
    private static final String GAE = "개";
    private static final String WON = "원";
    private static final String NONE = "없음";

    public static String convertMenuAndCountsMessage(Map<Food, Integer> orderMenu) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Food food : orderMenu.keySet()) {
            stringBuilder.append(food.getName())
                    .append(BLANK)
                    .append(orderMenu.get(food))
                    .append(GAE)
                    .append(LINE_SEPARATOR);
        }

        return stringBuilder.toString();
    }

    public static String convertPriceMessage(int price) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(price));
        String reverse = stringBuilder.reverse().toString();
        stringBuilder = new StringBuilder();
        for (int i=0; i<reverse.length(); i++) {
            if (reverse.charAt(i) == HYPHEN) {
                stringBuilder.insert(0, reverse.charAt(i));
                break;
            }

            if (i != 0 && i % 3 == 0) {
                stringBuilder.insert(0, COMMA);
            }

            stringBuilder.insert(0, reverse.charAt(i));
        }
        stringBuilder.append(WON).append(LINE_SEPARATOR);

        return stringBuilder.toString();
    }

    public static String convertGiveawaysMessage(List<GiveawayEvent> giveawayEvents) {
        StringBuilder StringBuilder = new StringBuilder();
        for (GiveawayEvent giveawayEvent : giveawayEvents) {
            StringBuilder.append(giveawayEvent.getGiveawayItemName())
                    .append(BLANK)
                    .append(giveawayEvent.getCount())
                    .append(GAE)
                    .append(LINE_SEPARATOR);
        }

        if (giveawayEvents.isEmpty()) {
            StringBuilder.append(NONE).append(LINE_SEPARATOR);
        }

        return StringBuilder.toString();
    }

    public static String convertBenefits(List<Event> events) {
        StringBuilder StringBuilder = new StringBuilder();
        for (Event event : events) {
            StringBuilder.append(event.getName()).append(COLON);
            String benefitPriceMessage = convertPriceMessage(event.getBenefit());
            StringBuilder.append(benefitPriceMessage);
        }

        if (events.isEmpty()) {
            StringBuilder.append(NONE).append(LINE_SEPARATOR);
        }

        return StringBuilder.toString();
    }

    public static String convertBadgeName(Badge badge) {
        return badge.getName();
    }
}
