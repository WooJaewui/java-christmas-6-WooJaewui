package christmas.domain.event;

public enum Badge {
    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;
    private static final int FIVE_THOUSAND = 5000;
    private static final int TEN_THOUSAND = 10000;
    private static final int TWENTY_THOUSAND = 20000;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Badge calculate(int totalBenefitPrice) {
        totalBenefitPrice = Math.abs(totalBenefitPrice);
        if (FIVE_THOUSAND <= totalBenefitPrice && totalBenefitPrice < TEN_THOUSAND) {
            return STAR;
        }

        if (TEN_THOUSAND <= totalBenefitPrice && totalBenefitPrice < TWENTY_THOUSAND) {
            return TREE;
        }

        if (TWENTY_THOUSAND <= totalBenefitPrice) {
            return SANTA;
        }

        return NONE;
    }
}
