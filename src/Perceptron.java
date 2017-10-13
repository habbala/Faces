public class Perceptron {
    private double[] input, weigth;
    private double bias, activationThreshold;

    public Perceptron(int inputs){
        this.input = new double[inputs];
        this.weigth = new double[inputs];
        this.bias = 0;
        activationThreshold = 0.5;
    }

    public int processInputs(){
        for(int i = 0 ; i < input.length ; i++){
            if (summateInput(i) >= 1){
                return 1;
            }
        }
        return 0;
    }

    int summateInput(int i){
        if(input[i] * weigth[i] >
                ActivationFunction.Sigmoid(activationThreshold)){
            return 1;
        } else {
            return 0;
        }
    }
}
