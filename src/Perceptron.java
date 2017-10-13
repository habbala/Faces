import java.util.Random;

public class Perceptron {

    private int faceMood;
    private double activationThreshold, sadFace, happyFace, mFace, angryFace;
    private Random randomValue, randomSadFace, randomHappyFace, randomMFace,
            randomAngryFace;
    private int bias, input;

    public Perceptron(int input){

        this.activationThreshold = 0.5;
        this.input = input;

        this.faceMood = 0;

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

    public int summateInput(){

        if(guessOutput() > ActivationFunction.Sigmoid
                (activationThreshold)){

            return 1;

        } else {

            return 0;
        }
    }

    public boolean isActivated(){

            if (summateInput() == 1){

                return true;
            }

        return false;
    }

    public double guessOutput(){

        if((sadFace * input) >= 1){

            faceMood = 1;
            return 1;
        }

        else if((happyFace * input) >= 1){

            faceMood = 2;
            return 1;
        }

        else if((mFace * input) >= 1){

            faceMood = 3;
            return 1;
        }

        else if((angryFace * input) >= 1){

            faceMood = 4;
            return 1;
        }

        else {

            System.out.println("Did not activate.");
            return 0;
        }
    }

    public int getFaceMood(){

        return faceMood;
    }
}
