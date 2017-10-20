/**
 * A class created to be able to create an activation value.
 */
public class ActivationFunction {

    /**
     * A Sigmoid function that calculates the activation value from the
     * from the intake value and returns the result.
     *
     * @param x the desired value to be calculated with.
     * @return the activation value between 0 and 1 as a double.
     */
    public static double Sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}
