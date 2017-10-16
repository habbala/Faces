public class NeuralNetwork {

    private Perceptron[][] perceptrons;
    private double[] netArray;
    private double ySize, xSize, error, deltaweight, learningRate;

    public NeuralNetwork(int ySize, int xSize) {

        this.ySize = ySize;
        this.xSize = xSize;

        netArray = new double[4];

        learningRate = 0.05;

        this.perceptrons = new Perceptron[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {
                perceptrons[y][x] = new Perceptron();
            }
        }

    }

    public FaceMood readInput(String input[][], int desiredOutput){

        double[] activationArray = new double[4];

        activationArray = calculateNetValues(input);

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {

                perceptrons[y][x].setWeight(FaceMood.SAD, calculateError
                        (activationArray[0], desiredOutput, Integer.parseInt
                                (input[y][x])));
                perceptrons[y][x].setWeight(FaceMood.HAPPY, calculateError
                        (activationArray[1], desiredOutput, Integer.parseInt
                                (input[y][x])));
                perceptrons[y][x].setWeight(FaceMood.MISCHIEVOUS, calculateError
                        (activationArray[2], desiredOutput, Integer.parseInt
                                (input[y][x])));
                perceptrons[y][x].setWeight(FaceMood.ANGRY, calculateError
                        (activationArray[3], desiredOutput, Integer.parseInt
                                (input[y][x])));
            }
        }

        //calculateError(activationArray, desiredOutput);
            //if(output[i] == 1){return output[i]}
        return FaceMood.SAD;
    }

    double[] calculateNetValues(String input[][]){

        int netSadValue = 0;
        int netHappyValue = 0;
        int netMischeivousValue = 0;
        int netAngryValue = 0;

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {

                int greyLevel = Integer.parseInt(input[y][x]);

                netSadValue += perceptrons[y][x].output(FaceMood.SAD, greyLevel);
                netHappyValue += perceptrons[y][x].output(FaceMood.HAPPY, greyLevel);
                netMischeivousValue += perceptrons[y][x].output(FaceMood
                        .MISCHIEVOUS, greyLevel);
                netAngryValue += perceptrons[y][x].output(FaceMood.ANGRY, greyLevel);
            }
        }

        netArray[0] = ActivationFunction.Sigmoid(netSadValue);
        netArray[1] = ActivationFunction.Sigmoid(netHappyValue);
        netArray[2] = ActivationFunction.Sigmoid(netMischeivousValue);
        netArray[3] = ActivationFunction.Sigmoid(netAngryValue);

        return netArray;
    }

    public double calculateError(double activationValue, int
            desiredOutput, int input){

        error = activationValue - desiredOutput;

        return learningRate * error * input;
    }
}
