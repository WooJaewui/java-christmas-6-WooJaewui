package christmas.domain.event;

import christmas.domain.dto.EventDto;

public class ChristmasEvent implements Event {
    private static final int MAX_DISCOUNT = -3400;
    private static final int CHRISTMAS_DAY = 25;
    private static final String NAME = "크리스마스 디데이 할인";
    private static final int ZERO = 0;
    private static final int HUNDRED = 100;
    private int benefit;

    @Override
    public int calculate(EventDto eventDto) {
        benefit = ZERO;
        if (!isEvent(eventDto)) {
            return benefit;
        }

        int reservationDate = eventDto.getReservationDate();
        benefit = MAX_DISCOUNT + (CHRISTMAS_DAY - reservationDate) * HUNDRED;
        return benefit;
    }

    @Override
    public boolean isEvent(EventDto eventDto) {
        int reservationDate = eventDto.getReservationDate();
        return reservationDate <= CHRISTMAS_DAY;
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
