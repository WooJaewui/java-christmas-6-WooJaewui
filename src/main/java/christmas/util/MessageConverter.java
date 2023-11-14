package christmas.util;

import christmas.domain.event.Badge;
import christmas.domain.event.category.Event;
import christmas.domain.event.category.GiveawayEvent;
import christmas.domain.food.Food;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class MessageConverter {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static String convertMenuAndCountsMessage(Map<Food, Integer> orderMenu) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Food food : orderMenu.keySet()) {
            stringBuilder.append(food.getName()).append(" ").append(orderMenu.get(food)).append("개").append(LINE_SEPARATOR);
        }

        return stringBuilder.toString();
    }

    public static String convertPriceMessage(int price) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(price));
        String reverse = stringBuilder.reverse().toString();
        stringBuilder = new StringBuilder();
        for (int i=0; i<reverse.length(); i++) {
            if (reverse.charAt(i) == '-') {
                stringBuilder.insert(0, reverse.charAt(i));
                break;
            }

            if (i != 0 && i % 3 == 0) {
                stringBuilder.insert(0, ",");
            }

            stringBuilder.insert(0, reverse.charAt(i));
        }
        stringBuilder.append("원").append(LINE_SEPARATOR);

        return stringBuilder.toString();
    }

    public static String convertGiveawaysMessage(List<GiveawayEvent> giveawayEvents) {
        StringBuilder StringBuilder = new StringBuilder();
        for (GiveawayEvent giveawayEvent : giveawayEvents) {
            StringBuilder.append(giveawayEvent.getGiveawayItemName())
                    .append(" ")
                    .append(giveawayEvent.getCount())
                    .append("개")
                    .append(LINE_SEPARATOR);
        }

        if (giveawayEvents.isEmpty()) {
            StringBuilder.append("없음").append(LINE_SEPARATOR);
        }

        return StringBuilder.toString();
    }

    public static String convertBenefits(List<Event> events) {
        StringBuilder StringBuilder = new StringBuilder();
        for (Event event : events) {
            StringBuilder.append(event.getName()).append(": ");
            String benefitPriceMessage = convertPriceMessage(event.getBenefit());
            StringBuilder.append(benefitPriceMessage);
        }

        if (events.isEmpty()) {
            StringBuilder.append("없음").append(LINE_SEPARATOR);
        }

        return StringBuilder.toString();
    }

    public static String convertBadgeName(Badge badge) {
        return badge.getName();
    }
}
