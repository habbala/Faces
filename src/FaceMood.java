public enum FaceMood {
    SAD, HAPPY, MISCHIEVOUS, ANGRY;

    public static FaceMood getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
