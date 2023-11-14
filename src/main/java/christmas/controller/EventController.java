package christmas.controller;

import christmas.domain.event.Badge;
import christmas.domain.event.category.Event;
import christmas.domain.event.category.GiveawayEvent;
import christmas.domain.food.Food;
import christmas.service.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class EventController {
    private EventPlanner eventPlanner;

    public EventController() {
        init();
    }

    private void init() {
        setReservationDate();
        orderMenu();
    }

    private void setReservationDate() {
        try {
            eventPlanner = new EventPlanner(InputView.readReservationDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setReservationDate();
        }
    }

    private void orderMenu() {
        try {
            eventPlanner.inputOrderMenu(InputView.readOrderMenu());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            orderMenu();
        }
    }

    public void preview() {
        printEventBenefitPreview();
    }

    private void printEventBenefitPreview() {
        printPreviewMessage();
        printOrderMenu();
        printTotalRegularPrice();
        printGiveaway();
        printBenefitList();
        printTotalBenefitPrice();
        printPaymentPrice();
        printBadge();
    }

    private void printPreviewMessage() {
        OutputView.printEventPreview();
    }

    private void printOrderMenu() {
        Map<Food, Integer> orderMenu = eventPlanner.getOrderMenu();
        StringBuilder sb = new StringBuilder();
        for (Food food : orderMenu.keySet()) {
            sb.append(food.getName()).append(" ").append(orderMenu.get(food)).append("개").append("\n");
        }

        OutputView.printMenu(sb.toString());
    }

    private void printTotalRegularPrice() {
        String totalRegularPrice = String.valueOf(eventPlanner.getTotalRegularPrice());
        StringBuilder stringBuilder = new StringBuilder(totalRegularPrice);
        String reverse = stringBuilder.reverse().toString();
        stringBuilder = new StringBuilder();
        for (int i=0; i<reverse.length(); i++) {
            if (i != 0 && i % 3 == 0) {
                stringBuilder.insert(0, ",");
            }

            stringBuilder.insert(0, reverse.charAt(i));
        }
        stringBuilder.append("원\n");

        OutputView.printTotalRegularPrice(stringBuilder.toString());
    }

    private void printGiveaway() {
        List<GiveawayEvent> giveawayEvents = eventPlanner.getGiveawayEvents();
        StringBuilder sb = new StringBuilder();
        for (GiveawayEvent giveawayEvent : giveawayEvents) {
            sb.append(giveawayEvent.getGiveawayItemName())
                    .append(" ")
                    .append(giveawayEvent.getCount())
                    .append("개")
                    .append("\n");
        }

        if (giveawayEvents.isEmpty()) {
            sb.append("없음").append("\n");
        }

        OutputView.printGiveawayEvent(sb.toString());
    }

    private void printBenefitList() {
        List<Event> events = eventPlanner.getEvents();
        StringBuilder sb = new StringBuilder();
        for (Event event : events) {
            sb.append(event.getName()).append(": ").append(event.getBenefit()).append("원").append("\n");
        }

        if (events.isEmpty()) {
            sb.append("없음").append("\n");
        }

        OutputView.printBenefitList(sb.toString());
    }

    private void printTotalBenefitPrice() {
        String totalRegularPrice = String.valueOf(eventPlanner.getTotalBenefitPrice());
        StringBuilder stringBuilder = new StringBuilder(totalRegularPrice);
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
        stringBuilder.append("원\n");

        OutputView.printBenefitPrice(stringBuilder.toString());
    }

    private void printPaymentPrice() {
        String paymentPrice = String.valueOf(eventPlanner.getPaymentPrice());
        StringBuilder stringBuilder = new StringBuilder(paymentPrice);
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
        stringBuilder.append("원\n");

        OutputView.printPaymentPrice(stringBuilder.toString());
    }

    private void printBadge() {
        Badge badge = eventPlanner.getBadge();
        String badgeMessage = badge.getName();

        OutputView.printEventBadge(badgeMessage);
    }
}
