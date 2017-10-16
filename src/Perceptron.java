import java.util.Random;

public class Perceptron {

    private FaceMood faceMood;
    private double activationThreshold, sadFace, happyFace, mFace, angryFace,
            trainRate;

    public Perceptron(){

        this.activationThreshold = 0.5;

        this.trainRate = 0.05;

        this.faceMood = FaceMood.getRandom();

        Random randomSadFaceWeight, randomHappyFaceWeight,
                randomMischievousFaceWeight, randomAngryFaceWeight;

        randomSadFaceWeight = new Random();
        randomHappyFaceWeight = new Random();
        randomMischievousFaceWeight = new Random();
        randomAngryFaceWeight = new Random();

        this.sadFace = randomSadFaceWeight.nextDouble();
        this.happyFace = randomHappyFaceWeight.nextDouble();
        this.mFace = randomMischievousFaceWeight.nextDouble();
        this.angryFace = randomAngryFaceWeight.nextDouble();

    }

    public double output(FaceMood faceMood, int input){

        if(faceMood.equals(FaceMood.SAD)){

            return sadFace * input;
        } else if(faceMood.equals(FaceMood.HAPPY)){

            return happyFace * input;
        } else if(faceMood.equals(FaceMood.MISCHIEVOUS)){

            return mFace * input;
        } else if(faceMood.equals(FaceMood.ANGRY)){

            return angryFace * input;
        }

        else {

            System.out.println("No faceMood found.");
            return 0;
        }
    }

    public FaceMood getFaceMood(){

        return faceMood;
    }

    public void backPropagationTraining(FaceMood faceMood){

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
