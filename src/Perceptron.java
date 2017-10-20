import java.util.Random;

/**
 * Perceptron is an artificial neuron, that uses backpropagation to learn.
 * The class may create inputs for each pixel in an image and output an
 * activation value. The image size may vary, but it must consist of 32 grey
 * levels.
 */
public class Perceptron {

    private static double learningRate;
    private double[][] weights, greyLevels;

    /**
     * Constructor. Creates inputs and weights for each pixel for a specific
     * image size.
     *
     * @param xSize Image width.
     * @param ySize Image height.
     */
    public Perceptron(int xSize, int ySize){

        learningRate = 0.01;

        weights = new double[xSize][ySize];
        greyLevels = new double[xSize][ySize];

        for(int x = 0 ; x < xSize ; x++){
            for(int y = 0 ; y < ySize ; y++){

                weights[x][y] = new Random().nextDouble();
            }
        }
    }

    /**
     * Summates each input and associated weight, whereafter the sum is run
     * through an activation function (Sigmoid).
     *
     * @param image The image to process.
     * @return sum Summated value of inputs after run through an activation
     * function.
     */
    public double output(String image[][]){

        double sum = 0;

        for(int x = 0 ; x < weights[0].length ; x++){
            for(int y = 0 ; y < weights[0].length ; y++){


                greyLevels[x][y] = (Double.parseDouble(image[x][y]) /32);

                sum += weights[x][y] * greyLevels[x][y];
            }
        }

        return ActivationFunction.Sigmoid(sum);
    }

    /**
     *
     * Method to adjust weights for each input. The weight is adjusted by a
     * sum calculated in the private calculateError function.
     *
     * @param activationValue What value the output function returned
     *                        previously, used to calculate the error.
     * @param desiredOutput 1 if the answer given by the perceptron was
     *                      correct. 0 if it was incorrect.
     */
    protected void setWeights(double activationValue, int desiredOutput){

        double newWeight = 0;

        for(int x = 0 ; x < weights.length ; x++){
            for(int y = 0 ; y < weights[0].length ; y++) {

                try {

                    newWeight = calculateError(activationValue,
                            desiredOutput, greyLevels[x][y]);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }

                weights[x][y] += newWeight;
            }
        }
    }

    /**
     * Calculates the error of a previous answer, given the previous answer,
     * whether it was correct or not, and the grey level value for the
     * related output.
     *
     * @param activationValue The value that the perceptron produced for the
     *                        same input previously.
     * @param desiredOutput 0 if the answer was wrong, 1 if it was correct.
     * @param input The grey level value that produced the answer.
     * @return double value by which a weight may be adjusted.
     */
    private double calculateError(double activationValue, int desiredOutput,
                                double input){

        double error = desiredOutput - activationValue;

        return learningRate * error * input;
    }
}
