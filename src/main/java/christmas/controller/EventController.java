package christmas.controller;

import christmas.service.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

import static christmas.util.MessageConverter.*;

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
        printOrderMenus();
        printTotalRegularPrice();
        printGiveaway();
        printBenefits();
        printTotalBenefitPrice();
        printPaymentPrice();
        printBadge();
    }

    private void printPreviewMessage() {
        OutputView.printEventPreview();
    }

    private void printOrderMenus() {
        String menuAndCountsMessage = convertMenuAndCountsMessage(eventPlanner.getOrderMenu());
        OutputView.printMenus(menuAndCountsMessage);
    }

    private void printTotalRegularPrice() {
        String totalRegularPriceMessage = convertPriceMessage(eventPlanner.getTotalRegularPrice());
        OutputView.printTotalRegularPrice(totalRegularPriceMessage);
    }

    private void printGiveaway() {
        String giveawaysMessage = convertGiveawaysMessage(eventPlanner.getGiveawayEvents());
        OutputView.printGiveawayEvent(giveawaysMessage);
    }

    private void printBenefits() {
        String benefitsMessage = convertBenefits(eventPlanner.getEvents());
        OutputView.printBenefits(benefitsMessage);
    }

    private void printTotalBenefitPrice() {
        String benefitPriceMessage = convertPriceMessage(eventPlanner.getTotalBenefitPrice());
        OutputView.printBenefitPrice(benefitPriceMessage);
    }

    private void printPaymentPrice() {
        String paymentPriceMessage = convertPriceMessage(eventPlanner.getPaymentPrice());
        OutputView.printPaymentPrice(paymentPriceMessage);
    }

    private void printBadge() {
        String badgeNameMessage = convertBadgeName(eventPlanner.getBadge());
        OutputView.printEventBadge(badgeNameMessage);
    }
}
