package christmas.domain.price;

public class TotalBenefitPrice {
    private int price;

    public int calculate(int totalBenefitPrice) {
        this.price = totalBenefitPrice;

        return price;
    }

    public int get() {
        return price;
    }
}
