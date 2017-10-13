import java.util.Random;

public class Perceptron {

    private double[] faceMood, weigth;
    private double activationThreshold, sadFace, happyFace, mFace, angryFace;
    private Random randomValue, randomSadFace, randomHappyFace, randomMFace,
            randomAngryFace;
    private int bias;

    public Perceptron(int inputs){

        this.faceMood = new double[inputs];//Byt ut mot inskickad input-array.
        this.weigth = new double[inputs];
        this.activationThreshold = 0.5;

        this.randomValue = new Random();
        this.bias = randomValue.nextInt(31);

        this.randomSadFace = new Random();
        this.randomHappyFace = new Random();
        this.randomMFace = new Random();
        this.randomAngryFace = new Random();

        this.sadFace = randomSadFace.nextDouble();
        this.happyFace = randomHappyFace.nextDouble();
        this.mFace = randomMFace.nextDouble();
        this.angryFace = randomAngryFace.nextDouble();

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

        for(int i = 0 ; i < faceMood.length ; i++){

            if (summateInput(i) >= 1){

                return true;
            }
        }

        return false;
    }

    public double guessOutput(){



        return bias;
    }
}
