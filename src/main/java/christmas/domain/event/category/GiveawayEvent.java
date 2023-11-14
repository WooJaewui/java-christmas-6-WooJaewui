package christmas.domain.event.category;

public interface GiveawayEvent extends Event {
    int getCount();
    String getGiveawayItemName();
}
