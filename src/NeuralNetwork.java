public class NeuralNetwork {

    private Perceptron2[] perceptrons;
    private double[] netArray;

    public NeuralNetwork(int ySize, int xSize) {

        netArray = new double[4];

        perceptrons = new Perceptron2[4];

        for(int i = 0 ; i < 4 ; i++){
            perceptrons[i] =
                    new Perceptron2(FaceMood.values()[i], ySize, xSize);
        }

    }

    public FaceMood readInput(String input[][], int desiredOutput){

        double[] activationArray = calculateNetValues(input);

        for(int i = 0; i < perceptrons.length; i++) {

            perceptrons[i].setWeights();
        }

        int b = 0;

        for(int i = 1; i < 4; i++){

            if(activationArray[b] < activationArray[i]){

                b = i;
            }
        }
        //calculateError(activationArray, desiredOutput);
            //if(output[i] == 1){return output[i]}
        return FaceMood.values()[b];
    }

    double[] calculateNetValues(String input[][]){

        for(int i = 0 ; i < perceptrons.length ; i++) {

            netArray[i] = ActivationFunction.Sigmoid(perceptrons[i].output
                    (input));

        }

        return netArray;
    }
}
