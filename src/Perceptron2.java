import java.util.Random;

public class Perceptron2 {

    private final FaceMood faceMood;
    private double error, learningRate;
    private double[][] weights;
    private int[][] greyLevels;

    public Perceptron2(FaceMood faceMood, int ySize, int xSize){

        this.learningRate = 0.1;

        this.faceMood = faceMood;

        weights = new double[ySize][xSize];
        greyLevels = new int[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++){
            for(int x = 0 ; x < xSize ; x++){
                weights[y][x] = new Random().nextDouble();
            }
        }
    }

    public FaceMood getFaceMood(){
        return faceMood;
    }

    public double output(String image[][]){

        double sum = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++){
                greyLevels[y][x] = Integer.parseInt(image[y][x]);
                sum += weights[y][x] * greyLevels[y][x];
            }
        }

        return ActivationFunction.Sigmoid(sum);
    }

    public void setWeights(double proposedAnswer, FaceMood correctAnswer){

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++){
                weights[y][x] += calculateError(proposedAnswer,correctAnswer
                                .getValue(), greyLevels[y][x]);
            }
        }
    }

    double calculateError(double activationValue, int desiredOutput, int input){

        error = activationValue - desiredOutput;

        return learningRate * error * input;
    }

    /*
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
    */
}
