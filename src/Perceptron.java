import java.util.Random;

public class Perceptron {

    private FaceMood faceMood;
    private double activationThreshold, sadFace, happyFace, mFace, angryFace,
            trainRate;
    private int input;

    public Perceptron(int input){

        this.activationThreshold = 0.5;
        this.input = input;

        this.trainRate = 0.05;

        Random randomSadFace, randomHappyFace, randomMFace, randomAngryFace;

        randomSadFace = new Random();
        randomHappyFace = new Random();
        randomMFace = new Random();
        randomAngryFace = new Random();

        this.sadFace = randomSadFace.nextDouble();
        this.happyFace = randomHappyFace.nextDouble();
        this.mFace = randomMFace.nextDouble();
        this.angryFace = randomAngryFace.nextDouble();

    }

    private int summateInput(){

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

    private double guessOutput(){

        if((sadFace * input) >= 1){

            faceMood = FaceMood.SAD;
            return 1;
        }

        if((happyFace * input) >= 1){

            faceMood = FaceMood.HAPPY;
            return 1;
        }

        if((mFace * input) >= 1){

            faceMood = FaceMood.MISCHIEVOUS;
            return 1;
        }

        if((angryFace * input) >= 1){

            faceMood = FaceMood.ANGRY;
            return 1;
        }

        else {

            System.out.println("Did not activate.");
            return 0;
        }
    }

    public FaceMood getFaceMood(){

        return faceMood;
    }

    public void train(FaceMood faceMood){

        if(this.faceMood != faceMood){

            switch(this.faceMood){

                case SAD:
                    sadFace += trainRate;
                    happyFace -= trainRate;
                    mFace -= trainRate;
                    angryFace -= trainRate;
                    break;

                case HAPPY:
                    sadFace -= trainRate;
                    happyFace += trainRate;
                    mFace -= trainRate;
                    angryFace -= trainRate;
                    break;

                case MISCHIEVOUS:
                    sadFace -= trainRate;
                    happyFace -= trainRate;
                    mFace += trainRate;
                    angryFace -= trainRate;
                    break;

                case ANGRY:
                    sadFace -= trainRate;
                    happyFace -= trainRate;
                    mFace -= trainRate;
                    angryFace += trainRate;
                    break;
            }
        }
    }
}
