package christmas.service;

import christmas.domain.dto.EventDto;
import christmas.domain.event.Badge;
import christmas.domain.event.Events;
import christmas.domain.event.category.Event;
import christmas.domain.event.category.GiveawayEvent;
import christmas.domain.food.Drink;
import christmas.domain.food.Food;
import christmas.domain.price.TotalRegularPrice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private final int reservationDate;
    private Map<Food, Integer> orderMenu = new HashMap<>();
    private final TotalRegularPrice totalRegularPrice;
    private final Events events;

    public EventPlanner(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.events = new Events();
    }

    public void inputOrderMenu(Map<Food, Integer> orderMenu) {
        this.orderMenu = new HashMap<>(orderMenu);
        totalRegularPrice.calculate(orderMenu);
        events.calculateEvent(new EventDto(reservationDate, totalRegularPrice.get(), orderMenu));
    }

    private boolean validate(List<Food> orderMenus) {
        return orderMenus.stream().allMatch(food -> food instanceof Drink);
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public Map<Food, Integer> getOrderMenu() {
        return orderMenu;
    }

    public int getTotalRegularPrice() {
        return totalRegularPrice.get();
    }

    public List<GiveawayEvent> getGiveawayEvents() {
        return events.getGiveawayEvents();
    }

    public Badge getBadge() {
        return events.getBadge();
    }

    public List<Event> getEvents() {
        return events.getEvents();
    }

    public int getTotalBenefitPrice() {
        return events.getTotalBenefitPrice();
    }

    public int getPaymentPrice() {
        return totalRegularPrice.get() + events.getTotalBenefitPrice();
    }
}
