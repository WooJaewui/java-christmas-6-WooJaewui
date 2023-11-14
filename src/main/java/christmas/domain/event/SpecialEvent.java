package christmas.domain.event;

import christmas.domain.dto.EventDto;
import christmas.domain.event.category.Event;

import java.util.Arrays;

public class SpecialEvent implements Event {
    private static final int[] SPECIAL_DISCOUNT_DAY = {3,10,17,24,25,31};
    private static final String NAME = "특별 할인";
    private int benefit;

    @Override
    public int calculate(EventDto eventDto) {
        if (!isEvent(eventDto)) {
            return benefit = 0;
        }

        return benefit = -1000;
    }

    public boolean isEvent(EventDto eventDto) {
        int reservationDate = eventDto.getReservationDate();
        long match = Arrays.stream(SPECIAL_DISCOUNT_DAY).filter(day -> day == reservationDate).count();

        return match > 0;
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
