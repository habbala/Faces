public class Perceptron {
    private double[] input, weigth;
    private double bias;
    private static ActivationFunction activationFunction;

    public Perceptron(int inputs, double bias){
        activationFunction = new ActivationFunction();
        this.input = new double[inputs];
        this.weigth = new double[inputs];
        this.bias = bias;
    }

    public double Summate(){
        double sum = 0;

        for(int i = 0 ; i < input.length ; i++){
            sum += input[i] * weigth[i];
        }

        return bias + sum;
    }

}
