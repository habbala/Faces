public class Perceptron {
    private double[] input, weigth;
    private double bias, activationThreshold;

    public Perceptron(int inputs){
        this.input = new double[inputs];
        this.weigth = new double[inputs];
        this.bias = bias;
        activationThreshold = 0.5;
    }

    private double Summate(){
        /*
        double[] sum = new double[input.length];

        for(int i = 0 ; i < input.length ; i++){
            sum[i] += input[i] * weigth[i];
        }

        return bias + sum;
        */
        return 0;
    }

    public int ProcessInput(){
        if(Summate() > ActivationFunction.Sigmoid(activationThreshold)){
            return 1;
        } else {
            return 0;
        }
    }
}
