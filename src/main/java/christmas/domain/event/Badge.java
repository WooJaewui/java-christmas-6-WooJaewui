package christmas.domain.event;

public enum Badge {
    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Badge calculate(int totalBenefitPrice) {
        if (5000 <= totalBenefitPrice && totalBenefitPrice < 10000) {
            return STAR;
        }

        if (10000 <= totalBenefitPrice && totalBenefitPrice < 20000) {
            return TREE;
        }

        if (20000 <= totalBenefitPrice) {
            return SANTA;
        }

        return NONE;
    }
}
