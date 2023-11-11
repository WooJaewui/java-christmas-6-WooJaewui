package christmas.domain.event;

import christmas.domain.food.Drink;

import static christmas.domain.food.Drink.*;

public class GiveawayEvent implements Event {
    private static final int EVENT_PRICE = 120000;
    private static final Drink EVENT_PRIZES = CHAMPAGNE;
    private static final String NAME = "증정 이벤트";
    private int count;
    private int price;

    public int calculate(int totalRegularPrice) {
        count = totalRegularPrice / EVENT_PRICE;
        price = count * -EVENT_PRIZES.getRegularPrice();

        return count;
    }

    public boolean isEvent(int totalRegularPrice) {
        return totalRegularPrice >= EVENT_PRICE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int getBenefit() {
        return price;
    }
}
