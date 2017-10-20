/**
 * An enum with each specific FaceMood stored as SAD = 0, HAPPY = 1,
 * MISCHIEVOUS = 2, ANGRY = 3.
 */
public enum FaceMood {
    SAD, HAPPY, MISCHIEVOUS, ANGRY;

    /**
     * This method makes it possible to get the FaceMood with the
     * corresponding value.
     *
     * @param x the desired value.
     * @return the FaceMood corresponding the desired value.
     */
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

    /**
     * This method makes it possible to get the value on the corresponging
     * FaceMood
     *
     * @return the value of the corresponding the current FaceMood.
     */
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
