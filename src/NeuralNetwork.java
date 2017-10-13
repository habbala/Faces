public class NeuralNetwork {

    private Perceptron[][] perceptrons;
    private double[] netArray;

    private double ySize, xSize, inputs, netSadValue, netHappyValue,
            netMischeivousValue, netAngryValue;

    public NeuralNetwork(int ySize, int xSize, int inputs) {

        this.ySize = ySize;
        this.xSize = xSize;
        this.inputs = inputs;
        this.netSadValue = 0;
        this.netHappyValue = 0;
        this.netMischeivousValue = 0;
        this.netAngryValue = 0;
        this.netSadValue = 0;

        this.perceptrons = new Perceptron[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {
                perceptrons[y][x] = new Perceptron(inputs);
            }
        }
    }

    public double[] calculateNetValues(){

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {

                netSadValue += perceptrons[y][x].output(FaceMood.SAD);
                netHappyValue += perceptrons[y][x].output(FaceMood.HAPPY);
                netMischeivousValue += perceptrons[y][x].output(FaceMood
                        .MISCHIEVOUS);
                netAngryValue += perceptrons[y][x].output(FaceMood.ANGRY);
            }
        }

        netArray[0] = ActivationFunction.Sigmoid(netSadValue);
        netArray[1] = ActivationFunction.Sigmoid(netHappyValue);
        netArray[2] = ActivationFunction.Sigmoid(netMischeivousValue);
        netArray[3] = ActivationFunction.Sigmoid(netAngryValue);

        return netArray;
    }

    public void calculateError(double[] netArray){


    }

    public void trainNetwork(){


    }
}
