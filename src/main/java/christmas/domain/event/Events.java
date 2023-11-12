package christmas.domain.event;

import christmas.domain.event.category.Event;

import java.util.ArrayList;
import java.util.List;

public class Events {
    private final List<Event> events;

    public Events(List<Event> events) {
        this.events = new ArrayList<>(events);
    }

    /*public int calculateEvent(EventDto eventDto) {
        events.stream().forEach();
    }

    public int getTotalRegularPrice() {

    }*/
}
