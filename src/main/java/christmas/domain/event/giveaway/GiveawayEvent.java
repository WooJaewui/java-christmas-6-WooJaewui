package christmas.domain.event.giveaway;

import christmas.domain.event.Event;

public interface GiveawayEvent extends Event {
    int getCount();
    String getGiveawayItemName();
}
