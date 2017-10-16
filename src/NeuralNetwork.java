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
                for(int i = 0; i < 4; i++) {

                    perceptrons[y][x].setWeight(FaceMood.values()[i],
                            calculateError(activationArray[i], desiredOutput,
                                    Integer.parseInt(input[y][x])));
                }
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
