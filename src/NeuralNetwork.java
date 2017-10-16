public class NeuralNetwork {

    private Perceptron[][] perceptrons;
    private double[] netArray;
    private double ySize, xSize;

    public NeuralNetwork(int ySize, int xSize) {

        this.ySize = ySize;
        this.xSize = xSize;

        netArray = new double[4];

        this.perceptrons = new Perceptron[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {
                perceptrons[y][x] = new Perceptron();
            }
        }

    }

    public FaceMood readInput(String input[][]){

        double[] output = new double[4];

        output = calculateNetValues(input);

        int highestOutput = 0;

        for(int i = 0 ; i < 3 ; i++){
            if(output[i] < output[i+1]){
                highestOutput = i+1;
            }
            //if(output[i] == 1){return output[i]}
        }
        return FaceMood.values()[highestOutput];
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

    public void calculateError(FaceMood answer){

    }
}
