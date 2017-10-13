import java.util.Random;

public class Perceptron {

    private double[] input, weigth;
    private double bias, activationThreshold;
    private Random randomValue;
    private int firstRandomValue;

    public Perceptron(int inputs){

        this.input = new double[inputs];//Byt ut mot inskickad input-array.
        this.weigth = new double[inputs];
        this.bias = 0;
        this.activationThreshold = 0.5;
        this.randomValue = new Random();
        this.firstRandomValue = randomValue.nextInt(31);
    }

    int summateInput(int i){

        if(guessOutput() * weigth[i] > ActivationFunction.Sigmoid
                (activationThreshold)){

            return 1;

        } else {

            return 0;
        }
    }

    public boolean isActivated(){

        for(int i = 0 ; i < input.length ; i++){

            if (summateInput(i) >= 1){

                return true;
            }
        }

        return false;
    }

    public double guessOutput(){


    }
}
