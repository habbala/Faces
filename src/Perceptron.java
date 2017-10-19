import java.util.Random;

public class Perceptron {

    private final FaceMood faceMood;
    private double learningRate;
    private double[][] weights, greyLevels;

    public Perceptron(FaceMood faceMood, int ySize, int xSize){

        this.learningRate = 0.01;

        this.faceMood = faceMood;

        weights = new double[ySize][xSize];
        greyLevels = new double[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++){
            for(int x = 0 ; x < xSize ; x++){
                weights[y][x] = new Random().nextDouble();
            }
        }
    }

    public double output(String image[][]){

        double sum = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++){


                greyLevels[y][x] = (Double.parseDouble(image[y][x]) /32);

                sum += weights[y][x] * greyLevels[y][x];
            }
        }

        sum = ActivationFunction.Sigmoid(sum);

        return sum;
    }

    public void setWeights(double activationValue, int desiredOutput){

        double newWeight = 0;

        for(int y = 0 ; y < weights[0].length ; y++){
            for(int x = 0 ; x < weights[0].length ; x++) {

                try {

                    newWeight = calculateError(activationValue,
                            desiredOutput, greyLevels[y][x]);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }

                weights[y][x] += newWeight;
            }
        }
    }

    private double calculateError(double activationValue, int desiredOutput,
                                double input){

        double error = desiredOutput - activationValue;

        return learningRate * error * input;
    }
}
