package christmas.domain.event.week;

import christmas.domain.dto.EventDto;
import christmas.domain.food.Dessert;
import christmas.domain.food.Food;

import java.util.Map;
import java.util.Set;

public class WeekdayEvent implements WeekEvent {
    private static final String NAME = "평일 할인";
    private static final int ZERO = 0;
    private int benefit;

    @Override
    public int calculate(EventDto eventDto) {
        benefit = ZERO;
        if (!isEvent(eventDto)) {
            return benefit;
        }

        Map<Food, Integer> orderMenu = eventDto.getOrderMenu();
        Set<Food> foods = orderMenu.keySet();
        for (Food food : foods) {
            if (food instanceof Dessert) {
                benefit -= ((Dessert)food).getWeekdayDiscount() * orderMenu.get(food);
            }
        }

        return benefit;
    }

    @Override
    public boolean isEvent(EventDto eventDto) {
        int reservationDate = eventDto.getReservationDate();
        Map<Food, Integer> orderMenu = eventDto.getOrderMenu();
        Set<Food> foods = orderMenu.keySet();
        long desertCount = foods.stream().filter(food -> food instanceof Dessert).count();

        return !isWeekend(reservationDate) && desertCount > ZERO;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getBenefit() {
        return benefit;
    }
}
