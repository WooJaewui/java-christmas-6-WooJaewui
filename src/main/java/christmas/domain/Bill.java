package christmas.domain;

import christmas.domain.dto.EventDto;
import christmas.domain.event.Events;
import christmas.domain.food.Drink;
import christmas.domain.food.Food;
import christmas.domain.price.TotalRegularPrice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bill {
    private final int reservationDate;
    private final Map<Food, Integer> orderMenu = new HashMap<>();
    private final TotalRegularPrice totalRegularPrice;
    private final Events events;

    public Bill(int reservationDate) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = new TotalRegularPrice();
        this.events = new Events();
    }

    public void inputOrderMenu(List<Food> orderMenus) {
        if (validate(orderMenus)) {
            return;
        }

        for (Food menu : orderMenus) {
            int count = 1;
            if (orderMenu.get(menu) != null) {
                count = orderMenu.get(menu) + 1;
            }

            orderMenu.put(menu, count);
        }

        totalRegularPrice.calculate(orderMenu);
        events.calculateEvent(new EventDto(reservationDate, totalRegularPrice.get(), orderMenu));
    }

    private boolean validate(List<Food> orderMenus) {
        return orderMenus.stream().allMatch(food -> food instanceof Drink);
    }
}
