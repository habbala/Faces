public enum FaceMood {
    SAD, HAPPY, MISCHIEVOUS, ANGRY;

    public static FaceMood getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static FaceMood fromInteger(int x) {
        switch(x) {
            case 0:
                return SAD;
            case 1:
                return HAPPY;
            case 2:
                return MISCHIEVOUS;
            case 3:
                return ANGRY;
        }
        return null;
    }
}
