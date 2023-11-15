package christmas.domain.event;

import christmas.domain.dto.EventDto;

public interface Event {
    int calculate(EventDto eventDto);

    boolean isEvent(EventDto eventDto);

    String getName();

    int getBenefit();
}
