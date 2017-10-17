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

    public int getValue(){
        switch(this) {
            case SAD:
                return 0;
            case HAPPY:
                return 1;
            case MISCHIEVOUS:
                return 2;
            case ANGRY:
                return 3;
        }
        return -1;
    }
}
