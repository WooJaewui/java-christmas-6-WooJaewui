package christmas.domain.dto;

import christmas.domain.food.Food;

import java.util.Map;

public class EventDto {
    private final int reservationDate;
    private final int totalRegularPrice;
    private final Map<Food, Integer> orderMenu;

    public EventDto(int reservationDate, int totalRegularPrice, Map<Food, Integer> orderMenu) {
        this.reservationDate = reservationDate;
        this.totalRegularPrice = totalRegularPrice;
        this.orderMenu = orderMenu;
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public int getTotalRegularPrice() {
        return totalRegularPrice;
    }

    public Map<Food, Integer> getOrderMenu() {
        return orderMenu;
    }
}
