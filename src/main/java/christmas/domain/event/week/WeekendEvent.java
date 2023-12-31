package christmas.domain.event.week;

import christmas.domain.dto.EventDto;
import christmas.domain.food.Food;
import christmas.domain.food.Main;

import java.util.Map;
import java.util.Set;

public class WeekendEvent implements WeekEvent {
    private static final String NAME = "주말 할인";
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
            if (food instanceof Main) {
                benefit -= ((Main)food).getWeekendDiscount() * orderMenu.get(food);
            }
        }

        return benefit;
    }

    @Override
    public boolean isEvent(EventDto eventDto) {
        int reservationDate = eventDto.getReservationDate();
        Map<Food, Integer> orderMenu = eventDto.getOrderMenu();
        Set<Food> foods = orderMenu.keySet();
        long mainCount = foods.stream().filter(food -> food instanceof Main).count();

        return isWeekend(reservationDate) && mainCount > ZERO;
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
