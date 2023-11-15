package christmas.domain.event.week;

import christmas.domain.event.Event;

public interface WeekEvent extends Event {
    int FRIDAY_MARK = 1;
    int SATURDAY_MARK = 2;
    int SEVEN = 7;

    default boolean isWeekend(int reservationDate) {
        return reservationDate % SEVEN == FRIDAY_MARK || reservationDate % SEVEN == SATURDAY_MARK;
    }
}
