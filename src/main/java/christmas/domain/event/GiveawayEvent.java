package christmas.domain.event;

import christmas.domain.food.Drink;

import static christmas.domain.food.Drink.*;

public class GiveawayEvent {
    private static final int EVENT_PRICE = 120000;
    private static final Drink EVENT_PRIZES = CHAMPAGNE;
    private int count;
    private int price;

    public int check(int totalRegularPrice) {
        count = totalRegularPrice / EVENT_PRICE;
        price = count * EVENT_PRIZES.getRegularPrice();

        return count;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
