import java.util.Random;

public class Perceptron2 {

    private final FaceMood faceMood;
    private double error, learningRate;
    private double[][] weights;
    private double[][] greyLevels;

    public Perceptron2(FaceMood faceMood, int ySize, int xSize){

        this.learningRate = 0.1;

        this.faceMood = faceMood;

        weights = new double[ySize][xSize];
        greyLevels = new double[ySize][xSize];

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
                greyLevels[y][x] = Integer.parseInt(image[y][x])/32;
                System.out.println();
                sum += weights[y][x] * greyLevels[y][x];
            }
        }

        return ActivationFunction.Sigmoid(sum);
    }

    public void setWeights(double proposedAnswer, int desiredOutput){

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++){
                double newWeight = calculateError(proposedAnswer,desiredOutput,
                        greyLevels[y][x]);
                weights[y][x] += newWeight;
            }
        }
    }

    double calculateError(double activationValue, int desiredOutput, double
            input){

        error = activationValue - desiredOutput;

        return learningRate * error * input;
    }
}
