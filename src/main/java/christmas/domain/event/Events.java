package christmas.domain.event;

import christmas.domain.dto.EventDto;
import christmas.domain.event.category.Event;
import christmas.domain.event.category.GiveawayEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.event.Badge.NONE;

public class Events {
    private static final int EVENT_APPLIED_PRICE = 10000;
    private final List<Event> events;
    private Badge badge;
    private final Map<String, Integer> benefitDetails = new HashMap<>();

    public Events() {
        this.events = Arrays.asList(
                new ChristmasEvent(),
                new WeekdayEvent(),
                new WeekendEvent(),
                new SpecialEvent(),
                new ChampaignGiveawayEvent()
        );
        badge = NONE;
    }

    public void calculateEvent(EventDto eventDto) {
        if (!isEvents(eventDto.getTotalRegularPrice())) {
            return;
        }

        calculateIndividualEvent(eventDto);
        calculateBenefitDetails(eventDto);
        badge = badge.calculate(getTotalBenefitPrice());
    }

    private boolean isEvents(int totalRegularPrice) {
        return totalRegularPrice >= EVENT_APPLIED_PRICE;
    }

    private void calculateIndividualEvent(EventDto eventDto) {
        events.forEach(event -> event.calculate(eventDto));
    }

    private void calculateBenefitDetails(EventDto eventDto) {
        List<Event> eventList = events.stream().filter(event -> event.isEvent(eventDto)).toList();
        eventList.forEach(event -> benefitDetails.put(event.getName(), event.getBenefit()));
    }

    public int getTotalBenefitPrice() {
        return events.stream().mapToInt(Event::getBenefit).sum();
    }

    public int getDiscountPrice() {
        return events.stream()
                .filter(event -> !(event instanceof GiveawayEvent))
                .mapToInt(Event::getBenefit)
                .sum();
    }

    public List<GiveawayEvent> getGiveawayEvents() {
        return events.stream()
                .filter(event -> event instanceof GiveawayEvent)
                .filter(event -> ((GiveawayEvent) event).getCount() > 0)
                .map(event -> (GiveawayEvent)event)
                .toList();
    }

    public Badge getBadge() {
        return badge;
    }

    public Map<String, Integer> getBenefitDetails() {
        return new HashMap<>(benefitDetails);
    }

    public List<Event> getEvents() {
        return events.stream().filter(event -> event.getBenefit() != 0).toList();
    }
}
