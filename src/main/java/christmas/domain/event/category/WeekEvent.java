package christmas.domain.event.category;

public interface WeekEvent extends Event{
    int FRIDAY_MARK = 1;
    int SATURDAY_MARK = 2;

    default boolean isWeekend(int reservationDate) {
        return reservationDate % 7 == FRIDAY_MARK || reservationDate % 7 == SATURDAY_MARK;
    }
}
