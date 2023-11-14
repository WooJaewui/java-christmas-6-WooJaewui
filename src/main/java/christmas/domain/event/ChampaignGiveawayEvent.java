package christmas.domain.event;

import christmas.domain.dto.EventDto;
import christmas.domain.event.category.GiveawayEvent;
import christmas.domain.food.Drink;

import static christmas.domain.food.Drink.CHAMPAGNE;

public class ChampaignGiveawayEvent implements GiveawayEvent {
    private static final int EVENT_PRICE = 120000;
    private static final Drink EVENT_PRIZES = CHAMPAGNE;
    private static final String NAME = "증정 이벤트";
    private int count;
    private int price;

    @Override
    public int calculate(EventDto eventDto) {
        if (!isEvent(eventDto)) {
            return count = 0;
        }

        int totalRegularPrice = eventDto.getTotalRegularPrice();
        count = totalRegularPrice / EVENT_PRICE;
        price = count * -EVENT_PRIZES.getRegularPrice();

        return count;
    }

    @Override
    public boolean isEvent(EventDto eventDto) {
        int totalRegularPrice = eventDto.getTotalRegularPrice();
        return totalRegularPrice >= EVENT_PRICE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getBenefit() {
        return price;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getGiveawayItemName() {
        return EVENT_PRIZES.getName();
    }
}
