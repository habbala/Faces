public class ActivationFunction {
    public static double Sigmoid(double x){
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }
}
